package com.zsy.bmw.dao;

import com.zsy.bmw.model.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by MAC on 27/04/2017.
 */
public interface NewsMapper {

    List<News> selectAll();

    @Cacheable(value = "newsContent", keyGenerator = "keyGenerator")
    String getContent(@Param("id") Integer id);

    int insertNews(News news);

    void insertContent(News news);

    List<News> getNewsByCreator(@Param("creatorName") String creatorName);

    @Cacheable(value = "newsRecommend", keyGenerator = "keyGenerator")
    List<News> selectRecommend();

    News getNewsById(@Param("id") Integer id);
}


