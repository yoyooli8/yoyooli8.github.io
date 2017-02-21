package com.ai.wxy.frame.springboot.services.cms.beans;

import java.util.List;

import com.ai.wxy.frame.springboot.services.cms.CNodeType;

/**
 * 模板结点
 * @author wxy
 * @date   2017-02-12
 */
public class Template extends BaseBean{
    private List<TemplateProp> tplprops; 
    public Template(){
        super();
        setNodeType(CNodeType.TPL_NODE.getType());
    }
    public List<TemplateProp> getTplprops(){
        return tplprops;
    }
    public void setTplprops(List<TemplateProp> tplprops){
        this.tplprops = tplprops;
    }
    public void addTplProp(TemplateProp tplProp){
        this.tplprops.add(tplProp);
    }
}
