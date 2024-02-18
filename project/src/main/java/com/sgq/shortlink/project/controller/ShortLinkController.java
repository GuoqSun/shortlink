package com.sgq.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgq.shortlink.project.common.convention.result.Result;
import com.sgq.shortlink.project.common.convention.result.Results;
import com.sgq.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.sgq.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.sgq.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.sgq.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.sgq.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/shortlink/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/shortlink/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }


}
