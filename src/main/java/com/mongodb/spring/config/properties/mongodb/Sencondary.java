package com.mongodb.spring.config.properties.mongodb;

/**
 * <pre>
 *     MongoDB replica set - Sencondary 접속 정보
 * </pre>
 */
public class Sencondary {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
