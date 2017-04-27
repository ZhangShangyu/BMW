package com.zsy.bmw.dao;

import com.zsy.bmw.model.News;

import java.util.List;

/**
 * Created by MAC on 27/04/2017.
 */
public interface NewsMapper {

    List<News> selectAll();
}
