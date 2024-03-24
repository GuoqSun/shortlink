package com.sgq.shortlink.project.mq.consumer;


import com.sgq.shortlink.project.common.convention.exception.ServiceException;
import com.sgq.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.sgq.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import com.sgq.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import static com.sgq.shortlink.project.common.constant.RedisKeyConstant.DELAY_QUEUE_STATS_KEY;

/**
 * 延迟记录短链接统计组件
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsConsumer implements InitializingBean {

    private final RedissonClient redissonClient;
    private final ShortLinkService shortLinkService;
    // 消息队列幂等性处理器，用于确保消息处理的唯一性和幂等性
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    public void onMessage() {
        // 创建一个单线程的Executor，用于消费消息
        Executors.newSingleThreadExecutor(
                        runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setName("delay_short-link_stats_consumer");
                            thread.setDaemon(Boolean.TRUE);
                            return thread;
                        })
                .execute(() -> {
                    // 获取阻塞双端队列和对应的延迟队列
                    RBlockingDeque<ShortLinkStatsRecordDTO> blockingDeque = redissonClient.getBlockingDeque(DELAY_QUEUE_STATS_KEY);
                    RDelayedQueue<ShortLinkStatsRecordDTO> delayedQueue = redissonClient.getDelayedQueue(blockingDeque);
                    // 无限循环，不断消费消息
                    for (; ; ) {
                        try {
                            // 从延迟队列中获取一个统计记录
                            ShortLinkStatsRecordDTO statsRecord = delayedQueue.poll();
                            if (statsRecord != null) {
                                if (!messageQueueIdempotentHandler.isMessagePrecessed(statsRecord.getKeys())) {
                                    // 判断这个消息是否执行完成
                                    if (messageQueueIdempotentHandler.isAccomplish(statsRecord.getKeys())) {
                                        return;
                                    }
                                    throw new ServiceException("消息未完成流程，需要消息队列重试");
                                }
                                try {
                                    shortLinkService.shortLinkStats(null, null, statsRecord);
                                } catch (Throwable ex) {
                                    // 如果处理失败，删除处理标记并记录错误日志
                                    messageQueueIdempotentHandler.delMessagePrecessed(statsRecord.getKeys());
                                    log.error("延迟记录短链接监控消费异常", ex);
                                }
                                // 标记消息处理完成
                                messageQueueIdempotentHandler.setAccomplish(statsRecord.getKeys());
                                continue;
                            }
                            // 如果队列为空，暂停一段时间（500毫秒）
                            LockSupport.parkUntil(500);
                        } catch (Throwable ignored) {
                        }
                    }
                });
    }

    // 在所有属性设置之后自动调用onMessage方法开始消费消息
    @Override
    public void afterPropertiesSet() throws Exception {
        onMessage();
    }
}
