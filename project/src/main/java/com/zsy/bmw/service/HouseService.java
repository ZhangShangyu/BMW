package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.HouseMapper;
import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        houseMapper.insertHouse(house);
        for (String imgUrl : house.getImgUrls()) {
            houseMapper.insertHouseImg(house.getHouseId(), imgUrl);
        }
        for (Integer tagId : house.getTagIds()) {
            houseMapper.insertHouseTag(house.getHouseId(), tagId);
        }
    }

    public House getHouseDetail(Integer id) {
        House house = houseMapper.getHouseById(id);
        house.setImgUrls(houseMapper.getHouseImgs(id));
        house.setTagNames(houseMapper.getTagNames(houseMapper.getHouseTagIds(id)));
        return house;
    }

    public List<House> getHouseByCondition(HouseCondition condition) {
        handleCondition(condition);
        executePagination(condition);
        List<Integer> houseIds = houseMapper.getHouseIdsByCondition(condition);
        List<House> result = new ArrayList<>();
        for (Integer id : houseIds) {
            result.add(getHouseBrief(id));
        }
        return result;
    }

    private House getHouseBrief(Integer id) {
        House house = houseMapper.getHouseBriefById(id);
        house.setHeadImg(houseMapper.getHeadImg(id));
        house.setTagNames(houseMapper.getTagNames(houseMapper.getHouseTagIds(id)));
        return house;
    }

    private void handleCondition(HouseCondition condition) {
        String price = condition.getPrice();
        if (price != null) {
            String minPrice = price.split("-")[0];
            String maxPrice = price.split("-")[1];
            condition.setMinPrice(Float.parseFloat(minPrice));
            condition.setMaxPrice(Float.parseFloat(maxPrice));
        }
        String area = condition.getArea();
        if (area != null) {
            String minArea = area.split("-")[0];
            String maxArea = area.split("-")[1];
            condition.setMinArea(Integer.parseInt(minArea));
            condition.setMaxArea(Integer.parseInt(maxArea));
        }
        List<Integer> tagIds = new ArrayList<>();
        if (condition.getRegion() != null) {
            tagIds.add(condition.getRegion());
        }
        if (condition.getType() != null) {
            tagIds.add(condition.getType());
        }
        if (condition.getDec() != null) {
            tagIds.add(condition.getDec());
        }
        if (condition.getRoute() != null) {
            tagIds.add(condition.getRoute());
        }
        if (condition.getStation() != null) {
            tagIds.add(condition.getStation());
        }
        if (tagIds.size() != 0) {
            condition.setTagIds(tagIds);
        }
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
