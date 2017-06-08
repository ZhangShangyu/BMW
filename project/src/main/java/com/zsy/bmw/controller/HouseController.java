package com.zsy.bmw.controller;

import com.zsy.bmw.model.BrowseCount;
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
    public Result getHouseDetail(@RequestParam("id") Integer houseId) {
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

    @RequestMapping("house-by-me")
    public Result getHouseByCreator(@RequestParam("name") String creatorName) {
        List<House> houses = houseService.getHouseByCreator(creatorName);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(houses);
        return result;
    }

    @RequestMapping("addcount")
    public Result addBrowseCount(BrowseCount browseCount) {
        houseService.addBrowseCount(browseCount);
        return new Result(Constant.OK_CODE, Constant.OK);
    }

    @RequestMapping("similar")
    public Result getSimilarHouses(@RequestParam("houseId") Integer houseId) {
        List<House> simHouses = houseService.getSimilarHouses(houseId);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(simHouses);
        return result;
    }

    @RequestMapping("recommend")
    public Result getRecommend(@RequestParam("userId") Integer userId,
                               @RequestParam("type") Integer type) {
        List<House> rcmdHouses = houseService.getRecommendHouses(userId, type);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(rcmdHouses);
        return result;
    }

    @RequestMapping("browsed")
    public Result getBrowsedHouses(@RequestParam("userId") Integer userId,
                                   @RequestParam("type") Integer type) {
        List<House> browsedHouses = houseService.getBrowsedHouses(userId, type);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(browsedHouses);
        return result;
    }
}
