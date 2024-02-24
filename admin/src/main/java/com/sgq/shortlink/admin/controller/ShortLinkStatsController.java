package com.sgq.shortlink.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgq.shortlink.admin.common.convention.result.Result;
import com.sgq.shortlink.admin.remote.ShortLinkRemoteService;
import com.sgq.shortlink.admin.remote.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.sgq.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import com.sgq.shortlink.admin.remote.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.sgq.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/api/shortlink/admin/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkRemoteService.oneShortLinkStats(requestParam);
    }

    /**
     * 单个短链接指定时间内访问记录监控数据
     */
    @GetMapping("/api/shortlink/admin/v1/accessRecord")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkRemoteService.shortLinkStatsAccessRecord(requestParam);
    }

}
