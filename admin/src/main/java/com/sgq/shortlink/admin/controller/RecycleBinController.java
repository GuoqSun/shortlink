package com.sgq.shortlink.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgq.shortlink.admin.common.convention.result.Result;
import com.sgq.shortlink.admin.common.convention.result.Results;
import com.sgq.shortlink.admin.remote.ShortLinkRemoteService;
import com.sgq.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import com.sgq.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.sgq.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回收站管理控制层
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    /**
     * 后续更改为SpringCloud
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 保存回收站
     */
    @PostMapping("/api/shortlink/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 分页查询回收站短链接
     */
    @GetMapping("/api/shortlink/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }

}
