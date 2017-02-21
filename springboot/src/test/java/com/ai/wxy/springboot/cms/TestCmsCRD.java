package com.ai.wxy.springboot.cms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.wxy.frame.springboot.services.cms.TPropType;
import com.ai.wxy.frame.springboot.services.cms.UpdateUtil;
import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;
import com.ai.wxy.frame.springboot.services.cms.beans.ContentProp;
import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;
import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;
import com.ai.wxy.frame.springboot.services.cms.service.ContentNodeServiceI;
import com.ai.wxy.frame.springboot.services.cms.service.FolderNodeServiceI;
import com.ai.wxy.frame.springboot.services.cms.service.TemplateServiceI;

public class TestCmsCRD extends BaseTest{
    @Autowired
    private ContentNodeServiceI contentService;
    @Autowired
    private TemplateServiceI tplService;
    @Autowired
    private FolderNodeServiceI folderService;
    @Test
    public void testCreateFolders(){
//        FolderNode appFolder = createFolder("和教育","-1");
//        String appFolderUid  = folderService.saveFolderNode(appFolder);
//        Assert.assertNotNull(appFolderUid);
//        System.out.println("-----appfd-------->"+appFolderUid);
//        String appFolderUid = "58ac0fae6f71a97dec3c9074";
        
//        FolderNode tplFolder = createFolder("模板文件",appFolderUid);
//        String tplFolderUid  = folderService.saveFolderNode(tplFolder);
//        Assert.assertNotNull(tplFolderUid);
//        System.out.println("-----tplfd-------->"+tplFolderUid);
//        String tplFolderUid = "58ac0fae6f71a97dec3c9075";
        
//        Template brandTpl    = createBrandTpl(tplFolderUid);
//        String brandUid      = tplService.saveTemplate(brandTpl);
//        Assert.assertNotNull(brandUid);
//        System.out.println("-----tpl-------->"+brandUid);
        String brandUid="58ac0fae6f71a97dec3c9076";
        
//        FolderNode contentFolder = createFolder("数据文件",appFolderUid);
//        String contentFolderUid  = folderService.saveFolderNode(contentFolder);
//        Assert.assertNotNull(contentFolderUid);
        Template template = tplService.getTemplateById(brandUid);
        Assert.assertNotNull(template);
//        System.out.println("-----content-------->"+contentFolderUid);
        String contentFolderUid = "58ac0fae6f71a97dec3c9077";
        
        ContentNode xmdata = createContentNode("小米",1,template,contentFolderUid);
        List<ContentProp> xmProps = getBaseProps(template);
        ContentProp xmlogo = createContentProp("logo","品牌LOGO",TPropType.PIC_PROP.getType(),"XIAOMI.png",null);
        xmProps.add(xmlogo);
        xmdata.setProps(xmProps);
        String nodeId = contentService.saveContentNode(xmdata);
        Assert.assertNotNull(nodeId);
        System.out.println("-----xm-------->"+nodeId);
        
        ContentNode hwdata = createContentNode("华为",2,template,contentFolderUid);
        List<ContentProp> hwProps = getBaseProps(template);
        ContentProp hwlogo = createContentProp("logo","品牌LOGO",TPropType.PIC_PROP.getType(),"HUAWEI.png",null);
        hwProps.add(hwlogo);
        hwdata.setProps(hwProps);
        nodeId = contentService.saveContentNode(hwdata);
        Assert.assertNotNull(nodeId);
        System.out.println("-----hw-------->"+nodeId);
        
        ContentNode opdata = createContentNode("OPPO",4,template,contentFolderUid);
        List<ContentProp> opProps = getBaseProps(template);
        ContentProp oplogo = createContentProp("logo","品牌LOGO",TPropType.PIC_PROP.getType(),"OPPO.png",null);
        opProps.add(oplogo);
        opdata.setProps(opProps);
        nodeId = contentService.saveContentNode(opdata);
        Assert.assertNotNull(nodeId);
        System.out.println("-----op-------->"+nodeId);
        
        ContentNode vvdata = createContentNode("VIVO",5,template,contentFolderUid);
        List<ContentProp> vvProps = getBaseProps(template);
        ContentProp vvlogo = createContentProp("logo","品牌LOGO",TPropType.PIC_PROP.getType(),"VIVO.png",null);
        vvProps.add(vvlogo);
        vvdata.setProps(vvProps);
        nodeId = contentService.saveContentNode(vvdata);
        Assert.assertNotNull(nodeId);
        
        System.out.println("-----vv-------->"+nodeId);
    }
    
    protected ContentNode createContentNode(String cname,Integer order ,Template template,String puuid){
        ContentNode content = new ContentNode();
        content.setName(cname);
        content.setDesc(cname);
        content.setValide(1);
        content.setOrder(order);
        content.setTemplateUid(template.getUuId());
        content.setPuuId(puuid);
        
        setCreateInfo(content);
        
        return content;
    }
    protected List<ContentProp> getBaseProps(Template template){
        List<ContentProp> props = new ArrayList<ContentProp>();
        
        List<TemplateProp> tplProps = template.getTplprops();
        for(TemplateProp tplProp:tplProps){
            
            ContentProp prop = createContentProp(tplProp);
            if(prop!=null){
                props.add(prop);
            }
        }
        
        return props;
    }
    protected ContentProp createContentProp(TemplateProp tplProp){
        String name   = tplProp.getName();
        String lable  = tplProp.getLable();
        Integer type  = tplProp.getType();
        Object defVal = tplProp.getDefautVal();
        if(UpdateUtil.isBaseProp(name)){
            return createContentProp(name,lable,type,null,defVal);
        }
        return null;
    }
    protected ContentProp createContentProp(String name,String lable,Integer type,Object val,Object defVal){
        ContentProp prop = new ContentProp();
        prop.setName(name);
        prop.setLable(lable);
        prop.setPropVal(getPropVal(val,defVal,type));
        
        return prop;
    }
    protected Object getPropVal(Object val,Object defVal,Integer type){
        if(val!=null){
            return val;
        }
        if(defVal!=null){
            return defVal;
        }
        
        if(type.intValue() == TPropType.DATE_PROP.getType().intValue()){
            return Calendar.getInstance().getTime();
        }else if(type.intValue() == TPropType.FILE_PROP.getType().intValue()){
            return "";
        }else if(type.intValue() == TPropType.PIC_PROP.getType().intValue()){
            return "";
        }else if(type.intValue() == TPropType.TIME_PROP.getType().intValue()){
            return Calendar.getInstance().getTime();
        }else if(type.intValue() == TPropType.TXT_PROP.getType().intValue()){
            return "";
        }else if(type.intValue() == TPropType.AREA_PROP.getType().intValue()){
            return "";
        }
        return null;
    }
    protected Template createBrandTpl(String puuid){
        Template brandTpl = createTemplate("手机品牌",puuid);
        TemplateProp logo = createTemplateProp("logo","品牌LOGO",TPropType.PIC_PROP,7,"default.png",Boolean.FALSE,Boolean.FALSE);
        logo.setValidates("picTypes");
        
        brandTpl.addTplProp(logo);
        
        return brandTpl;
    }
    protected Template createTemplate(String tplName,String puuid){
        Template template = new Template();
        template.setName(tplName);
        template.setPuuId(puuid);
        template.setValide(1);
        template.setTplprops(createBaseTplProps());
        setCreateInfo(template);
        
        return template;
    }
    protected List<TemplateProp> createBaseTplProps(){
        List<TemplateProp> props = new ArrayList<TemplateProp>();
        props.add(createTemplateProp("name","名称",TPropType.TXT_PROP,1,"",Boolean.FALSE,Boolean.FALSE));
        props.add(createTemplateProp("desc","描述",TPropType.TXT_PROP,2,"",Boolean.FALSE,Boolean.FALSE));
        props.add(createTemplateProp("createrUname","创建用户",  TPropType.TXT_PROP,3,"",Boolean.FALSE,Boolean.TRUE));
        props.add(createTemplateProp("createrUid",  "创建用户ID",TPropType.TXT_PROP,4,"",Boolean.TRUE, Boolean.FALSE));
        props.add(createTemplateProp("createTime",  "创建时间",TPropType.DATE_PROP,5,"",Boolean.FALSE, Boolean.TRUE));
        props.add(createTemplateProp("updateUname", "更新用户",  TPropType.TXT_PROP,6,"",Boolean.FALSE,Boolean.TRUE));
        props.add(createTemplateProp("updateUid",   "更新用户Id",TPropType.TXT_PROP,7,"",Boolean.TRUE, Boolean.FALSE));
        props.add(createTemplateProp("updateTime",  "更新时间",TPropType.DATE_PROP,8,"",Boolean.FALSE, Boolean.TRUE));
        return props;
    }
    
    protected TemplateProp createTemplateProp(String name,String lable,TPropType propType,Integer order,Object defaultVal,Boolean isHide,Boolean isReadOnly){
        TemplateProp prop = new TemplateProp();
        prop.setName(name);
        prop.setLable(lable);
        prop.setType(propType.getType());
        prop.setDefautVal(defaultVal);
        prop.setOrder(order);
        prop.setValide(1);
        prop.setIsHide(isHide);
        prop.setIsReadOnly(isReadOnly);
        setCreateInfo(prop);
        
        return prop;
    }
    protected FolderNode createFolder(String appName,String puuid){
        FolderNode folder = new FolderNode();
        folder.setName(appName);
        folder.setFolderFlag("appName");
        folder.setDesc(appName);
        folder.setValide(1);
        folder.setPuuId(puuid);
        setCreateInfo(folder);
        
        return folder;
    }
}
