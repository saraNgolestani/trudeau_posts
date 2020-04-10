package io.github.sarangolestani.controllers;

import io.github.sarangolestani.domain.CNNPost;
import io.github.sarangolestani.domain.TwitterPost;
import io.github.sarangolestani.domain.vo.CNNPostVO;
import io.github.sarangolestani.domain.vo.TwitterPostVO;
import io.github.sarangolestani.mapper.CNNObjectMapper;
import io.github.sarangolestani.mapper.TwitterObjectMapper;
import io.github.sarangolestani.services.CNNApiService;
import io.github.sarangolestani.services.TwitterApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/content")
public class TrudeauPostsController {


    private final CNNApiService cnnApiService;
    private final TwitterApiService twitterApiService;

    @Autowired
    public TrudeauPostsController(CNNApiService cnnApiService, TwitterApiService twitterApiService) {
        this.cnnApiService = cnnApiService;
        this.twitterApiService = twitterApiService;
    }


    @GetMapping(path = {"/twitter","/{username}/twitter","/twitter/{maxPosition}","/{username}/twitter/{maxPosition}"},
            produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public ResponseEntity<List<TwitterPostVO>> getTweets(@PathVariable(value = "username", required = false) String username,
                                                         @PathVariable(value = "maxPosition", required = false) String maxPosition) throws Exception {
        List<TwitterPost> twitterPosts = new ArrayList<>();
        if(StringUtils.isEmpty(maxPosition)){
            twitterPosts  = twitterApiService.fetchContent(username,0);
        }
        else {
            twitterPosts = twitterApiService.fetchContent(username,Integer.valueOf(maxPosition));
        }

        List<TwitterPostVO> vos = twitterPosts.stream()
                .map(t -> TwitterObjectMapper.getVo(t))
                .collect(Collectors.toList());

        return ResponseEntity.ok(vos);
    }


    @GetMapping(path = {"/cnn","/{query}/cnn","/cnn/{maxPosition}","/{query}/cnn/{maxPosition}"},
            produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public ResponseEntity<List<CNNPostVO>> getCnnPosts(@PathVariable(value = "query", required = false) String query,
                                                       @PathVariable(value = "maxPosition",required = false) String maxPosition){
        List<CNNPost> cnnPosts = new ArrayList<>();
        if(StringUtils.isEmpty(maxPosition)){
             cnnPosts = cnnApiService.fetchContent(query, 0);

        }
        else {
            cnnPosts = cnnApiService.fetchContent(query,Integer.valueOf(maxPosition) );

        }
        List<CNNPostVO> vos = cnnPosts.stream()
                .map(n -> CNNObjectMapper.getVo(n))
                .collect(Collectors.toList());

        return ResponseEntity.ok(vos);
    }
}
