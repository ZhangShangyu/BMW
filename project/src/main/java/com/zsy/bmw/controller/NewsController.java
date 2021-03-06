package com.zsy.bmw.controller;

import com.zsy.bmw.model.News;
import com.zsy.bmw.service.NewsService;
import com.zsy.bmw.utils.Constant;
import com.zsy.bmw.utils.Result;
import com.zsy.bmw.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ZSY on 27/04/2017.
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UploadFileUtil uploadFileUtil;

    @RequestMapping(value = "/get")
    public Result getNews(News news) {
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(newsService.getNewsByPage(news));
        return result;
    }

    @RequestMapping(value = "/upload")
    public Result uploadPic(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        String fileUrl;
        try {
            fileUrl = uploadFileUtil.saveUploadedFiles(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(Constant.ERROR_CODE2, Constant.SAVE_FILE_ERROR);
        }
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(fileUrl);
        return result;
    }

    @RequestMapping(value = "/save")
    public Result saveNews(@RequestBody News news) {
        if (news.getCreatorName() == null || news.getTitle() == null
                || news.getTitlePic() == null || news.getNewsAbstract() == null
                || news.getContent() == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        newsService.insertNews(news);
        return new Result(Constant.OK_CODE, Constant.OK);
    }

    @RequestMapping(value = "/detail")
    public Result getContent(@RequestParam("id") Integer id) {
        News news = newsService.getContent(id);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(news);
        return result;
    }

    @RequestMapping(value = "/news-by-me")
    public Result getNewsByCreator(@RequestParam("name") String creatorName) {
        List<News> news = newsService.getNewsByCreator(creatorName);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(news);
        return result;
    }

    @RequestMapping(value = "recommend")
    public Result getRecommendNews() {
        List<News> news = newsService.getRecommendNews();
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(news);
        return result;
    }

}
