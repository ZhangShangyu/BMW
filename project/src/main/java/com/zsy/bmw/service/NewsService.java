package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.NewsMapper;
import com.zsy.bmw.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MAC on 27/04/2017.
 */
@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public List<News> getNewsByPage(News news) {
        executePagination(news);
        return newsMapper.selectAll();
    }

    public void insertNews(News news) {
        newsMapper.insertNews(news);
        newsMapper.insertContent(news);
    }

    public String getContent(Integer id) {
        return newsMapper.getContent(id);
    }

    private void executePagination(News news) {
        if (news.getPageNum() != null && news.getRows() != null) {
            PageHelper.startPage(news.getPageNum(), news.getRows());
        }
    }
}
