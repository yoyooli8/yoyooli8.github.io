package com.ai.wxy.frame.springboot.services.user.dao.impl;

import java.io.Serializable;
import com.ai.wxy.frame.springboot.services.user.dao.impl.CustomerSpecs;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.ai.wxy.frame.springboot.services.user.dao.CustomRepository;
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID>{
    private final EntityManager entityManager;
    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(CustomerSpecs.byAuto(entityManager, example),pageable);
    }
}
