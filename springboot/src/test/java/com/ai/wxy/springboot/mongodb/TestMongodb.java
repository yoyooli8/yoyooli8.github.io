package com.ai.wxy.springboot.mongodb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ai.wxy.frame.springboot.ApplicationStarter;
import com.ai.wxy.frame.springboot.services.cms.TPropType;
import com.ai.wxy.frame.springboot.services.cms.beans.BaseBean;
import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;
import com.ai.wxy.frame.springboot.services.cms.service.TemplateServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public class TestMongodb{
    @Autowired
    private TemplateServiceI tplService;
    
    public void testAddTpl(){
        Template template = new Template();
        template.setName("手机");
        template.setValide(1);
        setCreateInfo(template);
        template.setTplprops(getBaseProps());
        
        String tplId = tplService.saveTemplate(template);
        Assert.assertNotNull(tplId);
        System.out.println("======================>"+tplId);
    }
    
    public void testUpdTpl(){
        String uuid = "58a19c456f71a92b20a42dea";
        Template template = new Template();
        template.setUuId(uuid);
        template.setTplprops(getBaseProps());
        String tplId = tplService.upTemplateProps(template);
        Assert.assertNotNull(tplId);//58a19c456f71a92b20a42dea
        System.out.println("======================>"+tplId);
    }
    @Test
    public void testUpdTplProp(){
        String uuid = "58a19c456f71a92b20a42dea";
        TemplateProp nprop = new TemplateProp();
        nprop.setName("phonePic");
        nprop.setLable("手机图片");
        nprop.setType(TPropType.PIC_PROP.getType());
        nprop.setDefautVal("default.png");
        nprop.setValidates("uploadPicTypes");
        nprop.setOrder(1);
        nprop.setValide(1);
        setUpdInfo(nprop);
        
        boolean upded = tplService.updTemplateProp(uuid, nprop);
        Assert.assertTrue(upded);
    }
    private void setCreateInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setCreaterUid(0);
        bean.setCreaterUname("testUser");
        bean.setCreateTime(now);
        bean.setUpdateTime(now);
        bean.setUpdateUid(0);
        bean.setUpdateUname("testUser");
    }
    private void setUpdInfo(BaseBean bean){
        Date now = Calendar.getInstance().getTime();
        bean.setUpdateTime(now);
        bean.setUpdateUid(1);
        bean.setUpdateUname("testUpdUser");
    }
    private List<TemplateProp> getBaseProps(){
        List<TemplateProp> props = new ArrayList<TemplateProp>();
        
        TemplateProp nprop = new TemplateProp();
        nprop.setName("name");
        nprop.setLable("名称");
        nprop.setType(TPropType.TXT_PROP.getType());
        nprop.setDefautVal("");
        nprop.setOrder(1);
        nprop.setValide(1);
        setUpdInfo(nprop);
        
        TemplateProp lprop = new TemplateProp();
        lprop.setName("desc");
        lprop.setLable("描述");
        lprop.setType(TPropType.TXT_PROP.getType());
        lprop.setDefautVal("");
        lprop.setOrder(2);
        lprop.setValide(1);
        setUpdInfo(lprop);
        
        TemplateProp tprop = new TemplateProp();
        tprop.setName("createUid");
        tprop.setLable("创建用户Id");
        tprop.setType(TPropType.TXT_PROP.getType());
        tprop.setDefautVal("");
        tprop.setIsHide(Boolean.TRUE);
        tprop.setOrder(3);
        tprop.setValide(1);
        setUpdInfo(tprop);
        
        TemplateProp dprop = new TemplateProp();
        dprop.setName("createUser");
        dprop.setLable("创建用户");
        dprop.setType(TPropType.TXT_PROP.getType());
        dprop.setDefautVal("");
        dprop.setIsReadOnly(Boolean.TRUE);
        dprop.setOrder(4);
        dprop.setValide(1);
        setUpdInfo(dprop);
        
        TemplateProp vprop = new TemplateProp();
        vprop.setName("updUid");
        vprop.setLable("更新用户Id");
        vprop.setType(TPropType.TXT_PROP.getType());
        vprop.setIsHide(Boolean.TRUE);
        vprop.setDefautVal("");
        vprop.setOrder(5);
        vprop.setValide(1);
        setUpdInfo(vprop);
        
        TemplateProp hprop = new TemplateProp();
        hprop.setName("updUser");
        hprop.setLable("更新用户");
        hprop.setType(TPropType.TXT_PROP.getType());
        hprop.setIsReadOnly(Boolean.TRUE);
        hprop.setOrder(5);
        hprop.setValide(1);
        setUpdInfo(hprop);
        
        props.add(nprop);
        props.add(lprop);
        props.add(tprop);
        props.add(dprop);
        props.add(vprop);
        props.add(hprop);
        
        return props;
    }
    //@Test
    public void testSaveTpl(){
        Template template = new Template();
        template.setName("测试2---->");
//        org.springframework.boot.autoconfigure.mongo.MongoProperties a;
        String tplId = tplService.saveTemplate(template);
        Assert.assertNotNull(tplId);
        System.out.println("======================>"+tplId);
        
    }
    @Test
    public void getGetTpl(){
        Template template = new Template();
        template.setName("测试---->");
        
        String uid = "58a03d7d21ee79b668da0b6b";
        Template getTpl = tplService.getTemplateById(uid);
        Assert.assertNotNull(getTpl);
        Assert.assertEquals(template.getName(), getTpl.getName());
    }
    @Test
    public void testGetTemplateProp(){
        List<Template> tpls = tplService.getTemplateProp("name_0");
        Assert.assertNotNull(tpls);
        Assert.assertEquals(2, tpls.size());
    }
    @Test
    public void testUpsertProp(){
        String uid = "58a15f1d6f71a93ea0d6e32d";
        TemplateProp prop = new TemplateProp();
        prop.setName("name_0");
        prop.setOrder(1);
        prop.setValide(1);
        prop.setCreaterUid(0);
        prop.setCreaterUname("test");
        prop.setCreateTime(Calendar.getInstance().getTime());
        
        boolean uped = tplService.updTemplateProp(uid, prop);
        Assert.assertTrue(uped);
    }
    
    //@Test
    public void testUpdateTpl(){
        String uid = "58a15f1d6f71a93ea0d6e32d";
        
        Template template = new Template();
        template.setUuId(uid);
        template.setTplprops(getTplProps());
        
        String updTpl = tplService.upTemplateProps(template);
        Assert.assertNotNull(updTpl);
        System.out.println("======================>"+updTpl);
    }
    
    private List<TemplateProp> getTplProps(){
        List<TemplateProp> props = new ArrayList<TemplateProp>();
        
        for(int i=0;i<10;i++){
            TemplateProp prop = new TemplateProp();
            prop.setName("name_"+i);
            prop.setOrder(i+1);
            prop.setValide(1);
            
            props.add(prop);
        }
        
        return props;
    }
}
