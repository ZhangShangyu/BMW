package com.zsy.bmw.dao;

import com.zsy.bmw.model.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MAC on 27/04/2017.
 */
public interface NewsMapper {

    List<News> selectAll();

    String getContent(@Param("id") Integer id);

    int insertNews(News news);

    void insertContent(News news);

    List<News> getNewsByCreator(@Param("creatorName") String creatorName);
}
