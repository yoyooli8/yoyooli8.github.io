package com.ai.frame.test.springmvc.cache;
/**
 * 缓存接口类
 * @author a
 *
 */
public interface DataCache{
    /**
      * 获取数据
      * @brief   获取数据
      * @details 获取数据
      * @param key
      * @return
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2016年12月7日 下午1:16:51
      * @note wangxiangyang@ 2016年12月7日添加了此方法
     */
    public Object get(String key);
    /**
      * 缓存数据
      * @brief   缓存数据
      * @details 缓存数据
      * @param key
      * @param value
      * @return 
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2016年12月7日 下午1:17:24
      * @note wangxiangyang@ 2016年12月7日添加了此方法
     */
    public void put(String key, Object value);
    /**
     * 清空缓存数据
     * @brief   清空缓存数据
     * @details 清空缓存数据
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2016年12月7日 下午1:16:51
     * @note wangxiangyang@ 2016年12月7日添加了此方法
    */
    public void clear();
    /**
     * 删除缓存数据
     * @brief   删除缓存数据
     * @details 删除缓存数据
     * @param key
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2016年12月7日 下午1:16:51
     * @note wangxiangyang@ 2016年12月7日添加了此方法
    */
    public void delete(String key);
}
