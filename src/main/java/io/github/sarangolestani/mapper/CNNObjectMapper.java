package io.github.sarangolestani.mapper;

import io.github.sarangolestani.domain.CNNPost;
import io.github.sarangolestani.domain.srv.CNNSearchResult;
import io.github.sarangolestani.domain.vo.CNNPostVO;

import java.util.ArrayList;
import java.util.List;

public class CNNObjectMapper {

    public static List<CNNPost> getCNNPost(CNNSearchResult result){
        List<CNNPost> posts = new ArrayList<>();

        for (CNNSearchResult.Result news: result.getResult()){
            CNNPost cnnPost = new CNNPost();

            cnnPost.setBody(news.getBody());
            cnnPost.setHeadline(news.getHeadline());
            cnnPost.setLink(news.getUrl());
            cnnPost.setLastPublished(news.getLastPublishDate());

            posts.add(cnnPost);
        }

        return posts;
    }

    public static CNNPostVO getVo(CNNPost post){
        CNNPostVO vo = new CNNPostVO();

        vo.setContent(post.getBody());
        vo.setLink(post.getLink());
        vo.setTitle(post.getHeadline());
        vo.setPublishDate(post.getLastPublished());
        vo.setTonality(post.getTonality());

        return vo;
    }
}
