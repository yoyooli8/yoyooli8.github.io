package com.ai.frame.test.springmvc.mybatis.util;

import java.util.List;

public class PageRecord<T>{
    /**总条数**/
    private Integer totalCount;
    /**总页数**/
    private Integer totalPage;
    /**当前页数**/
    private Integer pageNum;
    /**每页显示的条数**/
    private Integer showCount;
    /**当页的数据记录**/
    private List<T>  pageDatas;
    
    public PageRecord(){}
    public PageRecord(List<T>  pageDatas,Integer totalCount,Integer showCount,Integer pageNum){
        this.pageDatas  = pageDatas;
        this.totalCount = totalCount;
        this.showCount  = showCount;
        this.pageNum    = pageNum;
        this.totalPage  = this.totalCount % this.showCount == 0 ? this.totalCount/ this.showCount : this.totalCount/ this.showCount + 1;
    }
    public Integer getTotalCount(){
        return totalCount;
    }
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
    }
    public Integer getTotalPage(){
        return totalPage;
    }
    public void setTotalPage(Integer totalPage){
        this.totalPage = totalPage;
    }
    public Integer getPageNum(){
        return pageNum;
    }
    public void setPageNum(Integer pageNum){
        this.pageNum = pageNum;
    }
    public Integer getShowCount(){
        return showCount;
    }
    public void setShowCount(Integer showCount){
        this.showCount = showCount;
    }
    public List<T> getPageDatas(){
        return pageDatas;
    }
    public void setPageDatas(List<T> pageDatas){
        this.pageDatas = pageDatas;
    }
}
