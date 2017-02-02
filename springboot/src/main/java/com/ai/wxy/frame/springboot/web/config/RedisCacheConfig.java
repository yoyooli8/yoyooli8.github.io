package com.ai.wxy.frame.springboot.web.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{
    /**
     *  缓存管理器
      * @brief   缓存管理器
      * @details 缓存管理器
      * @param redisTemplate
      * @return
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2017年2月1日 下午4:46:43
      * @note wangxiangyang@ 2017年2月1日添加了此方法
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
    }
    /**
      * redis模板操作类,类似于jdbcTemplate的一个类;
      * @brief   redis模板操作类,类似于jdbcTemplate的一个类;
      * @details redis模板操作类,类似于jdbcTemplate的一个类;
      *           虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
      *           这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
      *           自己的缓存类，比如：RedisStorage类;
      * @param factory 通过Spring进行注入，参数在application.properties进行配置；
      * @return
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2017年2月1日 下午4:48:07
      * @note wangxiangyang@ 2017年2月1日添加了此方法
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误;
        //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        //或者JdkSerializationRedisSerializer序列化方式;
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        
        return redisTemplate;
    }
    
    /**
     * 自定义key.
     * @brief   自定义key.
     * @details 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样.
     * @author wangxiangyang
     * @date 2017年2月1日 下午4:48:07
     * @note wangxiangyang@ 2017年2月1日添加了此方法
     */
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target,Method method,Object... params){
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                
                System.out.println("keyGenerator=" + sb.toString());
                return sb.toString();
            }
            
        };
    }
}
