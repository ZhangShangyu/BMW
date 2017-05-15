package com.zsy.bmw.model;

import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

public class House extends BaseEntity {
    private Integer HouseId;
    private String name;
    private String creatorName;
    private String createTime;
    private Integer price;
    private Integer cityId;
    private Integer regionId;
    private String position;
    private String address;
    private Integer area;

    private String headImg;

    private List<String> imgUrls;
    private List<Integer> tagIds;
    private String phone;
    private String contact;
    private String des;

    private List<String> tagNames;

    private Integer regionTag;
    private Integer typeTag;
    private Integer decTag;
    private Integer routeTag;
    private Integer stationTag;

    public Integer getRegionTag() {
        return regionTag;
    }

    public void setRegionTag(Integer regionTag) {
        this.regionTag = regionTag;
    }

    public Integer getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(Integer typeTag) {
        this.typeTag = typeTag;
    }

    public Integer getDecTag() {
        return decTag;
    }

    public void setDecTag(Integer decTag) {
        this.decTag = decTag;
    }

    public Integer getRouteTag() {
        return routeTag;
    }

    public void setRouteTag(Integer routeTag) {
        this.routeTag = routeTag;
    }

    public Integer getStationTag() {
        return stationTag;
    }

    public void setStationTag(Integer stationTag) {
        this.stationTag = stationTag;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHouseId() {
        return HouseId;
    }

    public void setHouseId(Integer houseId) {
        HouseId = houseId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
