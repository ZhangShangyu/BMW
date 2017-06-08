package com.zsy.bmw.dao;

import com.zsy.bmw.model.BrowseCount;
import com.zsy.bmw.model.House;
import com.zsy.bmw.model.HouseCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by ZSY on 01/05/2017.
 */

public interface HouseMapper {

    List<House> getTopHouses();

    String getHeadImg(@Param("houseId") Integer houseId);

    void insertHouse(House house);

    void insertHouseImg(@Param("houseId") Integer houseId, @Param("imgUrl") String imgUrl);

    @Cacheable(value = "house-detail", keyGenerator = "keyGenerator")
    House getHouseById(@Param("houseId") Integer houseId);

    House getHouseBriefById(@Param("houseId") Integer houseId);

    @Cacheable(value = "house-img", keyGenerator = "keyGenerator")
    List<String> getHouseImgs(@Param("houseId") Integer houseId);

    List<House> getHouseIdsByCondition(HouseCondition condition);

    List<House> getHouseByCreator(@Param("creatorName") String creatorName);

    Integer getBrowseCount(BrowseCount browseCount);

    void insertBrowseCount(BrowseCount browseCount);

    void updateBrowseCount(BrowseCount browseCount);

    List<Integer> getSimilarHouseIds(@Param("houseId") Integer houseId);

    List<Integer> getRcmdHouseIdsByUser(@Param("userId") Integer userId, @Param("type") Integer type);

    List<House> getRcmdHouseBySys(@Param("type") Integer type);

    List<House> getBrowsedHouses(@Param("userId") Integer userId, @Param("type") Integer type);
}
