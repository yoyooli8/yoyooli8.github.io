package com.ai.wxy.frame.springboot.services.cms.service;

import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;

public interface ContentNodeServiceI{
    public String saveContentNode(ContentNode content);
    public String updateContentNode(ContentNode content);
}
