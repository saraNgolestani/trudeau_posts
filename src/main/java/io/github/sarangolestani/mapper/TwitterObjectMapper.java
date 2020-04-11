package io.github.sarangolestani.mapper;

import io.github.sarangolestani.domain.TwitterPost;
import io.github.sarangolestani.domain.vo.TwitterPostVO;


public class TwitterObjectMapper {

    public static TwitterPostVO getVo(TwitterPost post){
        TwitterPostVO vo = new TwitterPostVO();
        vo.setContent(post.getText());
        vo.setTitle(post.getText().substring(0, Math.min(15, post.getText().length())));
        vo.setLink(post.getLink());
        vo.setTonality(post.getTonality());
        vo.setPublishDate(post.getDate());

        return vo;

    }
}
