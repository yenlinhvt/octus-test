package com.octus.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mongodb.MongoClient;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.octus.common")
public class AppConfig {

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;

	}

	/*
	 * Factory bean that creates the Mongo instance
	 */
	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("127.0.0.1",27017), "mongo-octus");
	}
}