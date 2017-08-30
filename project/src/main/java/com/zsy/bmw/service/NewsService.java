package com.zsy.bmw.service;

import com.github.pagehelper.PageHelper;
import com.zsy.bmw.dao.NewsMapper;
import com.zsy.bmw.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZSY on 27/04/2017.
 */
@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private RedisService redisService;

    public List<News> getNewsByCreator(String creatorName) {
        return newsMapper.getNewsByCreator(creatorName);
    }

    public List<News> getRecommendNews() {
        return newsMapper.selectRecommend();
    }

    public List<News> getNewsByPage(News news) {
        String cacheKey = "newsPage" + news.getPageNum();
        if (redisService.exists(cacheKey)) {
            return (List<News>) redisService.get(cacheKey);
        }
        news.setRows(5);
        executePagination(news);
        List<News> newsList = newsMapper.selectAll();
        redisService.set(cacheKey, newsList);
        return newsList;
    }

    public void insertNews(News news) {
        newsMapper.insertNews(news);
        newsMapper.insertContent(news);
    }

    public News getContent(Integer id) {
        News news = newsMapper.getNewsById(id);
        if (news != null) {
            news.setContent(newsMapper.getContent(id));
        }
        return news;
    }

    private void executePagination(News news) {
        if (news.getPageNum() != null && news.getRows() != null) {
            PageHelper.startPage(news.getPageNum(), news.getRows());
        }
    }
}
