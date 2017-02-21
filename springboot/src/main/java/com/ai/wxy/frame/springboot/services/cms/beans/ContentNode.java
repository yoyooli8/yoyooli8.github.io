package com.ai.wxy.frame.springboot.services.cms.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.wxy.frame.springboot.services.ContentBaseProp;
import com.ai.wxy.frame.springboot.services.cms.CNodeType;
import com.ai.wxy.frame.springboot.services.cms.UpdateUtil;

public class ContentNode extends BaseBean{
    private static final String LABLESUFIX = "_lable";
    private String templateUid;
    private String desc;
    private List<ContentProp> props;
    public ContentNode(){
        super();
        setNodeType(CNodeType.CONTENT_NODE.getType());
    }
    
    public String getTemplateUid(){
        return templateUid;
    }
    public void setTemplateUid(String templateUid){
        this.templateUid = templateUid;
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public List<ContentProp> getProps(){
        return props;
    }
    public void setProps(List<ContentProp> props){
        this.props = props;
    }
    
    public Map<String,Object> getContentMap(){
        Map<String,Object> content = new HashMap<String,Object>();
        //基本属性
        content.put(ContentBaseProp.PROP_TPLID.getName(),templateUid);
        content.put(ContentBaseProp.PROP_TPLID.getName() + LABLESUFIX, ContentBaseProp.PROP_TPLID.getLable());
        content.put(ContentBaseProp.PROP_NAME.getName(),this.getName());
        content.put(ContentBaseProp.PROP_NAME.getName() + LABLESUFIX,  ContentBaseProp.PROP_NAME.getLable());
        content.put(ContentBaseProp.PROP_DESC.getName(),desc);
        content.put(ContentBaseProp.PROP_DESC.getName() + LABLESUFIX,  ContentBaseProp.PROP_DESC.getLable());
        content.put(ContentBaseProp.PROP_CREATERUID.getName(),this.getCreaterUid());
        content.put(ContentBaseProp.PROP_CREATERUID.getName() + LABLESUFIX,  ContentBaseProp.PROP_CREATERUID.getLable());
        content.put(ContentBaseProp.PROP_CREATERUNAME.getName(),this.getCreaterUname());
        content.put(ContentBaseProp.PROP_CREATERUNAME.getName() + LABLESUFIX,  ContentBaseProp.PROP_CREATERUNAME.getLable());
        content.put(ContentBaseProp.PROP_CREATETIME.getName(),this.getCreateTime());
        content.put(ContentBaseProp.PROP_CREATETIME.getName() + LABLESUFIX,  ContentBaseProp.PROP_CREATETIME.getLable());
        content.put(ContentBaseProp.PROP_UPDATEUID.getName(),this.getUpdateUid());
        content.put(ContentBaseProp.PROP_UPDATEUID.getName() + LABLESUFIX,  ContentBaseProp.PROP_UPDATEUID.getLable());
        content.put(ContentBaseProp.PROP_UPDATEUNAME.getName(),this.getUpdateUname());
        content.put(ContentBaseProp.PROP_UPDATEUNAME.getName() + LABLESUFIX,  ContentBaseProp.PROP_UPDATEUNAME.getLable());
        content.put(ContentBaseProp.PROP_UPDATETIME.getName(),this.getUpdateTime());
        content.put(ContentBaseProp.PROP_UPDATETIME.getName() + LABLESUFIX,  ContentBaseProp.PROP_UPDATETIME.getLable());
        content.put(ContentBaseProp.PROP_VALIDE.getName(),this.getValide());
        content.put(ContentBaseProp.PROP_VALIDE.getName() + LABLESUFIX,  ContentBaseProp.PROP_VALIDE.getLable());
        content.put(ContentBaseProp.PROP_CONTENTID.getName(),UpdateUtil.getContentId());
        //其他属性
        if(props!=null){
            for(ContentProp prop:props){
                String name    = prop.getName();
                String lable   = prop.getLable();
                Object propval = prop.getPropVal();
                
                if(name!=null && name.length()>0 && !UpdateUtil.isBaseProp(name)){
                    content.put(name, propval);
                    content.put(name+"_lable", lable);
                }
            }
        }
        return content;
    }
}
