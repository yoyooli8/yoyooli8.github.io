package com.ai.wxy.frame.springboot.services.cms.conf;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;

@ConfigurationProperties(prefix = "spring.data.mymongodb")
public class MyMongoProperties{
    public static final int DEFAULT_PORT = 27017;
    /**
     * Mongo server host.
     */
    private String host;

    /**
     * Mongo server port.
     */
    private Integer port = null;

    /**
     * Mongo database URI. When set, host and port are ignored.
     */
    private String uri = "mongodb://localhost/test";

    /**
     * Database name.
     */
    private String database;
    
    /**
     * sha1Credential name.
     */
    private Boolean sha1Credential;
    /**
     * Authentication database name.
     */
    private String authenticationDatabase;

    /**
     * GridFS database name.
     */
    private String gridFsDatabase;

    /**
     * Login user of the mongo server.
     */
    private String username;

    /**
     * Login password of the mongo server.
     */
    private char[] password;

    /**
     * Fully qualified name of the FieldNamingStrategy to use.
     */
    private Class<?> fieldNamingStrategy;

    public Boolean getSha1Credential(){
        return sha1Credential;
    }

    public void setSha1Credential(Boolean sha1Credential){
        this.sha1Credential = sha1Credential;
    }

    public String getHost(){
        return host;
    }

    public void setHost(String host){
        this.host = host;
    }

    public Integer getPort(){
        return port;
    }

    public void setPort(Integer port){
        this.port = port;
    }

    public String getUri(){
        return uri;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getDatabase(){
        return database;
    }

    public void setDatabase(String database){
        this.database = database;
    }

    public String getAuthenticationDatabase(){
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase){
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getGridFsDatabase(){
        return gridFsDatabase;
    }

    public void setGridFsDatabase(String gridFsDatabase){
        this.gridFsDatabase = gridFsDatabase;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public char[] getPassword(){
        return password;
    }

    public void setPassword(char[] password){
        this.password = password;
    }

    public Class<?> getFieldNamingStrategy(){
        return fieldNamingStrategy;
    }

    public void setFieldNamingStrategy(Class<?> fieldNamingStrategy){
        this.fieldNamingStrategy = fieldNamingStrategy;
    }
    public String getMongoClientDatabase() {
        if (this.database != null) {
            return this.database;
        }
        return new MongoClientURI(this.uri).getDatabase();
    }
    public MongoClient createMongoClient(MongoClientOptions options,
            Environment environment) throws UnknownHostException {
        try {
            if (hasCustomAddress() || hasCustomCredentials()) {
                if (options == null) {
                    options = MongoClientOptions.builder().build();
                }
                List<MongoCredential> credentials = new ArrayList<MongoCredential>();
                if (hasCustomCredentials()) {
                    String database = this.authenticationDatabase == null
                            ? getMongoClientDatabase() : this.authenticationDatabase;
                    if(sha1Credential!=null && sha1Credential.equals(Boolean.TRUE)){
                        credentials.add(MongoCredential.createScramSha1Credential(this.username,
                                database, this.password));
                    }else{
                        credentials.add(MongoCredential.createCredential(this.username,
                                database, this.password));
                    }
                    
                }
                String host = this.host == null ? "localhost" : this.host;
                int port = determinePort(environment);
                return new MongoClient(
                        Collections.singletonList(new ServerAddress(host, port)),
                        credentials, options);
            }
            // The options and credentials are in the URI
            return new MongoClient(new MongoClientURI(this.uri, builder(options)));
        }
        finally {
            clearPassword();
        }
    }
    
    public void clearPassword() {
        if (this.password == null) {
            return;
        }
        for (int i = 0; i < this.password.length; i++) {
            this.password[i] = 0;
        }
    }
    private boolean hasCustomAddress() {
        return this.host != null || this.port != null;
    }

    private boolean hasCustomCredentials() {
        return this.username != null && this.password != null;
    }

    private int determinePort(Environment environment) {
        if (this.port == null) {
            return DEFAULT_PORT;
        }
        if (this.port == 0) {
            if (environment != null) {
                String localPort = environment.getProperty("local.mongo.port");
                if (localPort != null) {
                    return Integer.valueOf(localPort);
                }
            }
            throw new IllegalStateException(
                    "spring.data.mongodb.port=0 and no local mongo port configuration "
                            + "is available");
        }
        return this.port;
    }
    private Builder builder(MongoClientOptions options) {
        if (options != null) {
            return MongoClientOptions.builder(options);
        }
        return MongoClientOptions.builder();
    }
    
}
