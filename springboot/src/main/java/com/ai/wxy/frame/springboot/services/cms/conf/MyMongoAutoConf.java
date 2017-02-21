package com.ai.wxy.frame.springboot.services.cms.conf;

import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(MyMongoProperties.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class MyMongoAutoConf{
    private final MyMongoProperties properties;

    private final MongoClientOptions options;

    private final Environment environment;

    private MongoClient mongo;
    public MyMongoAutoConf(MyMongoProperties properties,
            ObjectProvider<MongoClientOptions> optionsProvider, Environment environment) {
        this.properties = properties;
        this.options = optionsProvider.getIfAvailable();
        this.environment = environment;
        
    }

    @PreDestroy
    public void close() {
        if (this.mongo != null) {
            this.mongo.close();
        }
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException{
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo(), this.properties.getDatabase());
        return mongoDbFactory;
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MongoClient mongo() throws UnknownHostException {
        this.mongo = this.properties.createMongoClient(this.options, this.environment);
        return this.mongo;
    }
    
}
