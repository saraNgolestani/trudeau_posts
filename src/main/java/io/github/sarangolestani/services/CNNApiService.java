package io.github.sarangolestani.services;

import com.google.gson.Gson;
import io.github.sarangolestani.domain.CNNPost;
import io.github.sarangolestani.domain.srv.CNNSearchResult;
import io.github.sarangolestani.domain.srv.TwitterSearchResult;
import io.github.sarangolestani.mapper.CNNObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class CNNApiService extends AbstractApiServices<CNNPost> {

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

        return CNNObjectMapper.getCNNPost(gson.fromJson(result.getBody(), CNNSearchResult.class));
    }
}
