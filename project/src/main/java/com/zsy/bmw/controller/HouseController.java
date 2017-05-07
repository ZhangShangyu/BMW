package com.zsy.bmw.controller;

import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import com.zsy.bmw.service.HouseService;
import com.zsy.bmw.utils.Constant;
import com.zsy.bmw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */
@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/tophouses")
    public Result getTopHouse() {
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(houseService.getTopHouse());
        return result;
    }

    @RequestMapping("/save")
    public Result uploadHouse(@RequestBody House house) {
        houseService.saveHouse(house);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        return result;
    }

    @RequestMapping("/detail")
    public Result getHouseBrief(@RequestParam("id") Integer houseId) {
        if (houseId == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        House house = houseService.getHouseDetail(houseId);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(house);
        return result;
    }

    @RequestMapping("/condition")
    public Result getHouseByCondition(HouseCondition condition) {
        List<House> houses = houseService.getHouseByCondition(condition);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(houses);
        return result;
    }
}
