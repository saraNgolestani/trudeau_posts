package io.github.sarangolestani.services;

import com.google.gson.Gson;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import io.github.sarangolestani.domain.CNNPost;
import io.github.sarangolestani.domain.srv.CNNSearchResult;
import io.github.sarangolestani.mapper.CNNObjectMapper;
import io.github.sarangolestani.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class CNNApiService extends AbstractApiServices<CNNPost> {

    @Value("${tone.analyser.iam.apikey}")
    String apiKey;
    @Value("${tone.analyser.url}")
    String ibmUrl;
    private RestTemplate restTemplate;
    private Gson gson;
    private String address = "https://search.api.cnn.io/content";

    private String deafultQuery = "Trudeau";

    @PostConstruct
    private void init(){
        restTemplate = new RestTemplate();
        gson = new Gson();
    }

    @Override
    public List<CNNPost> fetchContent(String query, int maxPosition) {
        ResponseEntity<String> result = null;
        if(maxPosition != 0){
            result = restTemplate.getForEntity(address.concat("?size="+String.valueOf(maxPosition))
                    .concat("&q="+ (query == null ? deafultQuery : query))
                    .concat("&from=0&page=1"), String.class);
        }
        else{
            result = restTemplate.getForEntity(address.concat("?size=25")
                    .concat("&q="+ (query == null ? deafultQuery : query))
                    .concat("&from=0&page=1"), String.class);
        }

        List<CNNPost> cnnPosts = CNNObjectMapper.getCNNPost(gson.fromJson(result.getBody(), CNNSearchResult.class));


        return getTonality(cnnPosts);
    }

    private List<CNNPost> getTonality(List<CNNPost> cnnPosts){
        for(CNNPost cnnPost:cnnPosts){
            String toneAnalysis = Utils.tonality(Utils.getLimitedSentences(cnnPost.getBody(), 3), apiKey, ibmUrl);
            cnnPost.setTonality(toneAnalysis);
        }

        return cnnPosts;
    }

}

