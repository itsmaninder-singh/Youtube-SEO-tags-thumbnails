package com.yt.tools.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class searchVideo {
    private Video primaryvideo;
    private List<Video> relatedvideos;

}
