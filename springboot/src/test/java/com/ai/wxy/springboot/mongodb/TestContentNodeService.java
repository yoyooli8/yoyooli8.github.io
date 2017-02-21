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
import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;
import com.ai.wxy.frame.springboot.services.cms.beans.ContentProp;
import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;
import com.ai.wxy.frame.springboot.services.cms.service.ContentNodeServiceI;
import com.ai.wxy.frame.springboot.services.cms.service.TemplateServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
@WebAppConfiguration
public class TestContentNodeService{
    @Autowired
    private ContentNodeServiceI contentService;
    @Autowired
    private TemplateServiceI tplService;
    
    public void testSaveContentNode(){
        String uuid = "58a19c456f71a92b20a42dea";
        Template template = tplService.getTemplateById(uuid);
        
        ContentNode content = setData2ContentNode(template);
        
        String nodeId = contentService.saveContentNode(content);
        Assert.assertNotNull(nodeId);
        System.out.println("----83f05c2101394450936c864277d2a190------------------------>"+nodeId);
        
    }
    @Test
    public void testUpdateContentNode(){
        String uuid = "58a19c456f71a92b20a42dea";
        Template template = tplService.getTemplateById(uuid);
        
        ContentNode content = setData2ContentNode(template);
        content.setUuId("58a2d4286f71a93aa42df0b1");

        String rtn = contentService.updateContentNode(content);
        Assert.assertNotNull(rtn);
        System.out.println("----83f05c2101394450936c864277d2a190------------------------>"+rtn);
    }
    
    private ContentNode setData2ContentNode(Template template){
        ContentNode content = new ContentNode();
        content.setName("测试手机");
        content.setDesc("测试手机");
        content.setValide(1);
        content.setTemplateUid(template.getUuId());
        //设置属性值
        List<ContentProp> props = new ArrayList<ContentProp>();
        List<TemplateProp> tplProps = template.getTplprops();
        for(TemplateProp tplProp:tplProps){
            String name   = tplProp.getName();
            String lable  = tplProp.getLable();
            Integer type  = tplProp.getType();
            Object defVal = tplProp.getDefautVal();
            
            ContentProp prop = new ContentProp();
            prop.setName(name);
            prop.setLable(lable);
            prop.setPropVal(getDefVal(defVal,type));
            
            props.add(prop);
        }
        content.setProps(props);
        setUpdInfo(content);
        return content;
    }
    private Object getDefVal(Object defVal,Integer type){
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
            return "测试文本";
        }else if(type.intValue() == TPropType.AREA_PROP.getType().intValue()){
            return "测试文本域";
        }
        return "";
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
}
