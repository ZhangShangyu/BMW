package com.zsy.bmw.controller;

import com.zsy.bmw.model.News;
import com.zsy.bmw.service.NewsService;
import com.zsy.bmw.utils.Constant;
import com.zsy.bmw.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MAC on 27/04/2017.
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/get")
    public Result getNews(News news) {
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(newsService.getNewsByPage(news));
        return result;
    }
}
