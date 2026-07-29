package com.yt.tools.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDetails {
    private String id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String channeltitle;
    private String publishat;
    private List<String> tags;


}
