package com.zsy.bmw.dao;

import com.zsy.bmw.model.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

public interface HouseMapper {

    List<House> getTopHouses();

    String getHeadImg(@Param("houseId") Integer houseId);

}
