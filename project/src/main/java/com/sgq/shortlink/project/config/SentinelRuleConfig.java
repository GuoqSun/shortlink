package com.sgq.shortlink.project.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化限流配置
 */
@Component
public class SentinelRuleConfig implements InitializingBean {
    // 实现InitializingBean接口必须重写的方法afterPropertiesSet。这个方法将在所有的bean属性被Spring容器设置之后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建一个FlowRule的列表，用于存放流控规则
        List<FlowRule> rules = new ArrayList<>();
        // 创建一个流控规则
        FlowRule createOrderRule = new FlowRule();
        // 设置资源名，这里的资源名“create_short-link”是需要被保护的资源
        // Sentinel通过资源名来识别不同的资源
        createOrderRule.setResource("create_short-link");
        // 设置流控规则的阈值类型，这里使用RuleConstant.FLOW_GRADE_QPS表示根据QPS（每秒请求数量）来限流
        createOrderRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置QPS的阈值为1，意味着当QPS超过1时，该资源的访问将会被限流
        createOrderRule.setCount(1);
        // 将定义的流控规则添加到规则列表中
        rules.add(createOrderRule);
        // 使用FlowRuleManager.loadRules加载并生效这些规则
        // Sentinel会根据这些规则来执行流量控制
        FlowRuleManager.loadRules(rules);
    }
}