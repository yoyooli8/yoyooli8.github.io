package com.ai.wxy.frame.springboot.services.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.ai.wxy.frame.springboot.services.dao.CustomRepository;
import static com.ai.wxy.frame.springboot.services.dao.impl.CustomerSpecs.*;
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID>{
    private final EntityManager entityManager;
    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(byAuto(entityManager, example),pageable);
    }
}