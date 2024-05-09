package me.zuuhyun.youtubeproject.controller;

import lombok.RequiredArgsConstructor;
import me.zuuhyun.youtubeproject.dto.VideoSettlementResponse;
import me.zuuhyun.youtubeproject.service.VideoSettlementService;
import me.zuuhyun.youtubeproject.util.Period;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class VideoSettlementApiController {
    private final VideoSettlementService videoSettlementService;

    /* 비디오 별 정산금액 아이디를 받고 일간 주간 한달 조회수를 가져오고 조회수별 단가 계산*/
    @GetMapping("/api/videosettle/videos/{id}")
    public ResponseEntity<List<VideoSettlementResponse>> getVideoSettlement(@PathVariable long id) {
        /*비디오아이디, 기간을 service에 전달 영상 조회수의 정산 금액을 받음*/
        List<VideoSettlementResponse> videoSettlementResponseList = new ArrayList<>();
        for(Period period : Period.values()) {
            double videoSettlement = videoSettlementService.getVideoSettlement(id, period);
            double adSettlement = videoSettlementService.getAdSettlement(id, period);
            double totalSettlement = videoSettlement + adSettlement;

            VideoSettlementResponse response = new VideoSettlementResponse();
            response.setPeriod(period.toString());
            response.setVideoId(id);
            response.setDate(getPeriodToString(period)); // 날짜 정보 설정 필요
            response.setVideoSettlement(videoSettlement);
            response.setAdSettlement(adSettlement);
            response.setTotalSettlement(totalSettlement);
            videoSettlementResponseList.add(response);
        }
        return ResponseEntity.ok()
                .body(videoSettlementResponseList);
    }

    public String getPeriodToString(Period period) {
        String startDate;
        String endDate = java.sql.Date.valueOf(LocalDate.now()).toString();
        if (period.toString().equals("WEEK")) {
            startDate = java.sql.Date.valueOf(LocalDate.now().minusWeeks(1)).toString();
        } else if (period.toString().equals("MONTH")) {
            startDate = java.sql.Date.valueOf(LocalDate.now().minusMonths(1)).toString();
        } else {
            return endDate;
        }
        return startDate + " ~ " + endDate;
    }
}
