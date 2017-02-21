package com.ai.wxy.frame.springboot.services.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ai.wxy.frame.springboot.services.cms.beans.Template;

public interface TemplateRepository extends MongoRepository<Template, String>{
    public Template findByUuId(String uuId);
    public List<Template> findByName(String name);
}
