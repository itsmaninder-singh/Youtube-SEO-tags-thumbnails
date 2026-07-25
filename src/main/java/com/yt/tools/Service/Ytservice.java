package com.yt.tools.Service;

import com.yt.tools.models.searchVideo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Ytservice {
    private final WebClient.Builder webClientBuilder;

    @Value("${youtube.api.key}")
    private String apikey;

    @Value("${youtube.api.base.url}")
    private String baseurl;
    @Value("${youtube.api.max.related.videos}")
    private int MaxrelatedVideos;



    public searchVideo searchVideos(String videoTitle) {
        List<String> videoIds = searchforVideoIds(videoTitle);
        return null;



    }

    private List<String> searchforVideoIds(String videoTitle) {
        SearchApiResponse response = webClientBuilder.baseUrl(baseurl).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("part","snippet")
                        .queryParam("q",videoTitle)
                        .queryParam("type", "video")
                        .queryParam("maxResult",MaxrelatedVideos)
                        .queryParam("key",apikey)
                        .build())
                .retrieve()



    }
    //inner dtos
    @Data
    static class SearchApiResponse {
        List<SearchItem> items;
    }

    @Data
    static class SearchItem {
        Id id;
    }

    @Data
    static class Id {
        String videoId;
    }

    @Data
    static class VideoApiResponse {
        List<VideoItem> items;
    }

}
