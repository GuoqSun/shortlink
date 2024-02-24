package com.sgq.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgq.shortlink.project.common.convention.result.Result;
import com.sgq.shortlink.project.common.convention.result.Results;
import com.sgq.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.sgq.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.sgq.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.sgq.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import com.sgq.shortlink.project.service.ShortLinkStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/api/shortlink/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }

    /**
     * 单个短链接指定时间内访问记录监控数据
     */
    @GetMapping("/api/shortlink/v1/accessRecord")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStatsAccessRecord(requestParam));
    }


}
