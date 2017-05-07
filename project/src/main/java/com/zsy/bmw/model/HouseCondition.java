package com.zsy.bmw.model;

import java.util.List;

/**
 * Created by ZSY on 08/05/2017.
 */
public class HouseCondition extends BaseEntity {
    private Integer region;
    private Integer type;
    private Integer station;
    private Integer route;
    private Integer dec;

    private String area;
    private String price;

    private Float minPrice;
    private Float maxPrice;
    private Integer minArea;
    private Integer maxArea;

    private List<Integer> tagIds;

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public Integer getDec() {
        return dec;
    }

    public void setDec(Integer dec) {
        this.dec = dec;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}