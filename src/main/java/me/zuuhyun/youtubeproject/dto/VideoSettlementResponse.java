package me.zuuhyun.youtubeproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VideoSettlementResponse {
    private String period;
    private Long videoId;
    private String date;
    private Long videoSettlement;
    private Long adSettlement;
    private Long totalSettlement;
}
