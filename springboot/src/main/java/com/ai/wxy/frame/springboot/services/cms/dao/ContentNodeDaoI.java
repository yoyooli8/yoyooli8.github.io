package com.ai.wxy.frame.springboot.services.cms.dao;

import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;

public interface ContentNodeDaoI{
    String saveContentNode(ContentNode content);
    String updateContentNode(ContentNode content);
}
