package com.mongodb.spring.config.properties.mongodb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <pre>
 *     MongoDB replica set 접속 정보 프로퍼티
 * </pre>
 */
@Configuration
@ConfigurationProperties("mongodb")
public class MongoDBProperties {
    private Master master;
    private List<Sencondary> sencondary;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<Sencondary> getSencondary() {
        return sencondary;
    }

    public void setSencondary(List<Sencondary> sencondary) {
        this.sencondary = sencondary;
    }
}

