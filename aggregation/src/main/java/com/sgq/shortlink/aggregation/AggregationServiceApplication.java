package com.sgq.shortlink.aggregation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 短链接应用
 */
@SpringBootApplication(scanBasePackages = "com.sgq.shortlink")
@EnableDiscoveryClient
@MapperScan(value = {
		"com.sgq.shortlink.admin.dao.mapper",
		"com.sgq.shortlink.project.dao.mapper"
})
public class AggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregationServiceApplication.class, args);
	}

}
