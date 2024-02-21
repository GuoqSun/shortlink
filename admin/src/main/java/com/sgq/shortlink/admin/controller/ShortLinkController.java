package com.sgq.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgq.shortlink.admin.common.convention.result.Result;
import com.sgq.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.sgq.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.sgq.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.sgq.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.sgq.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接后管控制层
 */
@RestController
public class ShortLinkController {
    /**
     * 后续更改为SpringCloud
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/api/shortlink/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/shortlink/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
