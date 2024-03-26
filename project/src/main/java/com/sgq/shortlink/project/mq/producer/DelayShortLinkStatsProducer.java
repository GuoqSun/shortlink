package com.sgq.shortlink.project.mq.producer;

import cn.hutool.core.lang.UUID;
import com.sgq.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.sgq.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;

/**
 * 延迟消费短链接统计发送者
 */
@Component
@Deprecated
@RequiredArgsConstructor
public class DelayShortLinkStatsProducer {

    private final RedissonClient redissonClient;

    /**
     * 发送延迟消费短链接统计
     *
     * @param statsRecord 短链接统计实体参数
     */
    public void send(ShortLinkStatsRecordDTO statsRecord) {
        // 为statsRecord设置一个唯一键，使用UUID生成这个唯一键
        statsRecord.setKeys(UUID.fastUUID().toString());
        // 获取一个阻塞双端队列（RBlockingDeque），这个队列用于存储即将被延迟处理的短链接统计信息
        RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY);
        // 通过redissonClient获取这个队列对应的延迟队列（RDelayedQueue）
        RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
        // 将短链接统计信息加入到延迟队列中，并设置5秒后执行
        // 这意味着，这条记录会在5秒后被处理，而不是立即处理
        delayedQueue.offer(statsRecord, 5, TimeUnit.SECONDS);
    }
}
