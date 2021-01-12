package com.fante.project.business.bizShopInfo.domain;

/**
 * <h3>Fante</h3>
 * <p></p>
 *
 * @author : cheng
 * @date : 2020-11-06 10:39
 */
public class ShopInfoVo{
    
    /** 旧接口查询字段 */
    private String recommend;
    private String name;
    private String audit;
    
    /**
     * 经度 必传字段
     */
    private String lng;
    
    /**
     * 纬度 必传字段
     */
    private String lat;
    
    /**
     * 分类id
     */
    private Long categoryId;
    
    /**
     * 距离（米）
     */
    private String distance;
    
    /**
     * 排序 
     * distance 为距离最近排序
     * sum      为销量排序
     */
    private String orderByCondition;
    
    public String getOrderByCondition(){
        return orderByCondition;
    }
    
    public void setOrderByCondition(String orderByCondition){
        this.orderByCondition = orderByCondition;
    }
    
    public String getRecommend(){
        return recommend;
    }
    
    public void setRecommend(String recommend){
        this.recommend = recommend;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAudit(){
        return audit;
    }
    
    public void setAudit(String audit){
        this.audit = audit;
    }
    
    public String getLng(){
        return lng;
    }
    
    public void setLng(String lng){
        this.lng = lng;
    }
    
    public String getLat(){
        return lat;
    }
    
    public void setLat(String lat){
        this.lat = lat;
    }
    
    public Long getCategoryId(){
        return categoryId;
    }
    
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    
    public String getDistance(){
        return distance;
    }
    
    public void setDistance(String distance){
        this.distance = distance;
    }
    
    
}
