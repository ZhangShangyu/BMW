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

    @RequestMapping(value = "/upload")
    public Result uploadPic(@RequestParam("file") MultipartFile uploadFile) {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        String fileUrl;
        try {
            fileUrl = UploadFileUtil.saveUploadedFiles(uploadFile);
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
        if (news.getCreatorId() == null || news.getTitle() == null
                || news.getTitlePic() == null || news.getNewsAbstract() == null
                || news.getContent() == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        newsService.insertNews(news);
        return new Result(Constant.OK_CODE, Constant.OK);
    }

    @RequestMapping(value = "/detail")
    public Result getContent(@RequestParam("id") Integer id) {
        if (id == null) {
            return new Result(Constant.ERROR_CODE1, Constant.PARAM_ERROR);
        }
        String content = newsService.getContent(id);
        Result result = new Result(Constant.OK_CODE, Constant.OK);
        result.setData(content);
        return result;
    }

    //save file
//    private String saveUploadedFiles(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            return "";
//        }
//        byte[] bytes = file.getBytes();
//        String fileName = DateUtil.getNowTime() + "-" + file.getOriginalFilename();
//        Path path = Paths.get("/Users/mac/Desktop/BMW/project/src/main/resources/static/upload/" + fileName);
//        Files.write(path, bytes);
//        return "http://localhost:8080/static/upload/" + fileName;
//    }
}
