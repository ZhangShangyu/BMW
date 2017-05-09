package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.HouseMapper;
import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

@Service
public class HouseService {
    @Autowired
    private HouseMapper houseMapper;

    public List<House> getTopHouse() {
        House _house = new House();
        _house.setRows(18);
        executePagination(_house);
        List<House> houses = houseMapper.getTopHouses();
        for (House house : houses) {
            house.setHeadImg(houseMapper.getHeadImg(house.getHouseId()));
        }
        return houses;
    }

    public void saveHouse(House house) {
        // TODO 插入数据后插入solr索引 可考虑用异步队列
        houseMapper.insertHouse(house);
        for (String imgUrl : house.getImgUrls()) {
            houseMapper.insertHouseImg(house.getHouseId(), imgUrl);
        }
        // TODO 考虑是否还需要TAG
        Integer id = house.getHouseId();
        houseMapper.insertHouseTag(id, house.getRegionTag());
        houseMapper.insertHouseTag(id, house.getTypeTag());
        houseMapper.insertHouseTag(id, house.getDecTag());
        if (house.getRouteTag() != 0) {
            houseMapper.insertHouseTag(id, house.getRouteTag());
        }
        if (house.getStationTag() != 0) {
            houseMapper.insertHouseTag(id, house.getStationTag());
        }
    }

    public House getHouseDetail(Integer id) {
        House house = houseMapper.getHouseById(id);
        house.setImgUrls(houseMapper.getHouseImgs(id));
        List<Integer> tagIds = houseMapper.getHouseTagIds(id);
        if (tagIds.size() != 0) {
            house.setTagNames(houseMapper.getTagNames(tagIds));
        } else {
            house.setTagNames(Collections.emptyList());
        }
        return house;
    }

    public List<House> getHouseByCondition(HouseCondition condition) {
        // TODO  1、 改变condition格式  2 、使用solr搜索出id list  3 、拼装house info
        handleCondition(condition);
        executePagination(condition);
        List<House> houses = houseMapper.getHouseIdsByCondition(condition);
        for (House house : houses) {
            house.setHeadImg(houseMapper.getHeadImg(house.getHouseId()));
            List<Integer> tagIds = houseMapper.getHouseTagIds(house.getHouseId());
            if (tagIds.size() != 0) {
                house.setTagNames(houseMapper.getTagNames(tagIds));
            } else {
                house.setTagNames(Collections.emptyList());
            }
        }
        return houses;
    }

    private void handleCondition(HouseCondition condition) {
        String price = condition.getPrice();
        if (price != null) {
            String minPrice = price.split("-")[0];
            String maxPrice = price.split("-")[1];
            condition.setMinPrice(Integer.parseInt(minPrice));
            condition.setMaxPrice(Integer.parseInt(maxPrice));
        }
        String area = condition.getArea();
        if (area != null) {
            String minArea = area.split("-")[0];
            String maxArea = area.split("-")[1];
            condition.setMinArea(Integer.parseInt(minArea));
            condition.setMaxArea(Integer.parseInt(maxArea));
        }
        condition.setRows(8);
    }

    private void executePagination(House house) {
        if (house.getPageNum() != null && house.getRows() != null) {
            PageHelper.startPage(house.getPageNum(), house.getRows());
        }
    }

    private void executePagination(HouseCondition condition) {
        if (condition.getPageNum() != null && condition.getRows() != null) {
            PageHelper.startPage(condition.getPageNum(), condition.getRows());
        }
    }
}
