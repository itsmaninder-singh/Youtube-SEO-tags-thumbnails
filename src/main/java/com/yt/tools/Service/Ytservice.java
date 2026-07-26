package com.yt.tools.Service;

import com.yt.tools.models.Video;
import com.yt.tools.models.searchVideo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

        if(videoIds.isEmpty()){
            return searchVideo.builder()
                    .primaryVideo(null)
                    .relatedVideos(Collections.emptyList())
                    .build();
        }

        String primaryVideoId = videoIds.get(0);

        List<String> relatedVideosd=videoIds.subList(1,Math.min(videoIds.size(),MaxrelatedVideos));

        Video primaryVideos = getVideoById(primaryVideoId);
        List<Video> realtedVideos = new ArrayList<>();

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
                .retrieve()// ye api ko data bhejta or response ke liye ready ho jata h
                .bodyToMono(SearchApiResponse.class)
                .block(); //ye mono ko wait krwata h or actual data deta h agr nhi likhoge to ek d data nhi milega

        if (response== null || response.items == null){
            return Collections.emptyList();
        }
        List<String> videoIds = new ArrayList<>();
        for(SearchItem item : response.items){
            videoIds.add(item.id.videoId);


        }
        return videoIds;



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
        List<videoItem> items;
    }

}
