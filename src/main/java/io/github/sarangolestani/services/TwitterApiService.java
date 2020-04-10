package io.github.sarangolestani.services;

import com.google.gson.Gson;
import io.github.sarangolestani.domain.TwitterPost;
import io.github.sarangolestani.domain.srv.TwitterSearchResult;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class TwitterApiService extends AbstractApiServices<TwitterPost> {
    private HttpClient defaultHttpClient;
    private Gson gson;

    private String defaultUsername = "JustinTrudeau";

    @PostConstruct
    private void init(){
        defaultHttpClient = HttpClients.createDefault();
        gson = new Gson();
    }

    @Override
    public List<TwitterPost> fetchContent(String query, int maxPosition) throws Exception{
        List<TwitterPost> twitterPosts = new ArrayList<>();
        String curser = null;
        outerLace: while (true) {

            String url = String.format("https://twitter.com/i/search/timeline?f=realtime&q=%s&src=typd&max_position=%s",
                    URLEncoder.encode(getAppendQuery(query), "UTF-8"), curser);

            TwitterSearchResult tsr = getSearchResults(url);
            Document doc = Jsoup.parse(tsr.getItems_html());
            curser = tsr.getMin_position();

            Elements tweets = doc.select("div.js-stream-tweet");

            if (tweets.size() != 0) {
                for (Element tweet : tweets) {
                    TwitterPost post = getTwitts(tweet, query);
                    twitterPosts.add(post);
                    if(maxPosition != 0){
                        if(twitterPosts.size() >= maxPosition)
                            break outerLace;

                    }else {
                        if(twitterPosts.size() >= 25)
                            break outerLace;
                    }


                }
            }
        }

        return twitterPosts;
    }


    private String getAppendQuery(String query){
        String appendQuery = "";
        if (query == null) {
            appendQuery += "from:"+defaultUsername;
        }else {
            appendQuery += "from:"+query;
        }

        appendQuery += " since:"+ "2018-01-01";

        return appendQuery;

    }

    private TwitterSearchResult getSearchResults(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpEntity resp = defaultHttpClient.execute(httpGet).getEntity();

        TwitterSearchResult tsr = gson.fromJson(EntityUtils.toString(resp), TwitterSearchResult.class);
        return tsr;
    }


    private TwitterPost getTwitts(Element tweet, String query){

        String txt = tweet.select("p.js-tweet-text").text().replaceAll("[^\\u0000-\\uFFFF]", "");
        txt = new String(txt.getBytes(), Charset.forName("utf-8"));
        long dateMs = Long.valueOf(tweet.select("small.time span.js-short-timestamp")
                .attr("data-time-ms"));

//                    String id = tweet.attr("data-tweet-id");
        String permalink = tweet.attr("data-permalink-path");


        Date date = new Date(dateMs);
        TwitterPost post = new TwitterPost();

        post.setLink("https://twitter.com"+permalink);
        post.setUsername(query != null ? query : defaultUsername);
        post.setText(txt);
        post.setDate(date);

        return post;

    }

}
