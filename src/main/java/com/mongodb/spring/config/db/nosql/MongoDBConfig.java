package com.mongodb.spring.config.db.nosql;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.spring.config.properties.mongodb.Master;
import com.mongodb.spring.config.properties.mongodb.MongoDBProperties;
import com.mongodb.spring.config.properties.mongodb.Sencondary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoDBConfig {
    private final MongoDBProperties mongoDBProperties;

    public MongoDBConfig(MongoDBProperties mongoDBProperties) {
        this.mongoDBProperties = mongoDBProperties;
    }

    @Bean
    public MongoClient mongoClient() {
        Master master = mongoDBProperties.getMaster();
        
        MongoCredential mongoCredential = MongoCredential.createCredential(master.getUsername()
                , master.getAuthDatabase()
                , master.getPassword().toCharArray());

        List<ServerAddress> serverAddresses = new ArrayList<>();
        serverAddresses.add(new ServerAddress(master.getHost(), master.getPort()));

        List<Sencondary> sencondarys = mongoDBProperties.getSencondary();
        for(Sencondary sencondary : sencondarys) {
            serverAddresses.add(new ServerAddress(sencondary.getHost(), sencondary.getPort()));
        }
        
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .credential(mongoCredential)
                .applyToClusterSettings(b -> b.hosts(serverAddresses))
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoDatabaseFactorySupport<MongoClient> mongoDatabaseFactorySupport(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoDBProperties.getMaster().getDatabase());
    }

    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactorySupport<MongoClient> mongoDatabaseFactorySupport) {
        return new MongoTransactionManager(mongoDatabaseFactorySupport);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactorySupport<MongoClient> mongoDatabaseFactorySupport) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactorySupport);
        ((MappingMongoConverter) mongoTemplate.getConverter()).setTypeMapper(new DefaultMongoTypeMapper(null));

        return mongoTemplate;
    }
}
