package com.sgq.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sgq.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 短链接操作系统访问监控实体
 */
@Data
@TableName("t_link_os_stats")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkOsStatsDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 日期
     */
    private Date date;

    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 操作系统
     */
    private String os;
}
