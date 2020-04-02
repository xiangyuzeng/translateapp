package cn.zengxiangyu.translate.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 11636
 * @date 2020-03-13 22:17
 * @Description: [一句话描述该类的功能]
 */
@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongodbURL;

    @Bean
    public MongoDatabase createMongoDatabase() {
        MongoClientURI mongoClientURI = new MongoClientURI(mongodbURL);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        return mongoDatabase;
    }
}
