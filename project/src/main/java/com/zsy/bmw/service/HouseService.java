package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.HouseMapper;
import com.zsy.bmw.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void executePagination(House house) {
        if (house.getPageNum() != null && house.getRows() != null) {
            PageHelper.startPage(house.getPageNum(), house.getRows());
        }
    }
}
