package com.zsy.bmw.controller;

import com.zsy.bmw.service.HouseService;
import com.zsy.bmw.utils.Constant;
import com.zsy.bmw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
