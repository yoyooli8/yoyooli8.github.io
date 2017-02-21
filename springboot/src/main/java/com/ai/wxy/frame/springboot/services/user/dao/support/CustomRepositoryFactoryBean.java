package com.ai.wxy.frame.springboot.services.user.dao.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.ai.wxy.frame.springboot.services.user.dao.impl.CustomRepositoryImpl;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID>{
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {// 2
        return new CustomRepositoryFactory(entityManager);
    }
    private static class CustomRepositoryFactory extends JpaRepositoryFactory {
        public CustomRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }
        
        @SuppressWarnings("unchecked")
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {// 4
            return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

        }
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 5
            return CustomRepositoryImpl.class;
        }
    }
}
