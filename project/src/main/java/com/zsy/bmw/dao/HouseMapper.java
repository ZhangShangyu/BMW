package com.zsy.bmw.dao;

import com.zsy.bmw.model.BrowseCount;
import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

public interface HouseMapper {

    List<House> getTopHouses();

    String getHeadImg(@Param("houseId") Integer houseId);

    void insertHouse(House house);

    void insertHouseImg(@Param("houseId") Integer houseId, @Param("imgUrl") String imgUrl);

    void insertHouseTag(@Param("houseId") Integer houseId, @Param("tagId") Integer tag);

    House getHouseById(@Param("houseId") Integer houseId);

    House getHouseBriefById(@Param("houseId") Integer houseId);

    List<String> getHouseImgs(@Param("houseId") Integer houseId);

    List<Integer> getHouseTagIds(@Param("houseId") Integer houseId);

    List<String> getTagNames(@Param("tagIds") List<Integer> tagIds);

    List<House> getHouseIdsByCondition(HouseCondition condition);

    String getTagName(@Param("id") Integer id);

    List<House> getHouseByCreator(@Param("creatorName") String creatorName);

    Integer getBrowseCount(BrowseCount browseCount);

    void insertBrowseCount(BrowseCount browseCount);

    void updateBrowseCount(BrowseCount browseCount);
}
