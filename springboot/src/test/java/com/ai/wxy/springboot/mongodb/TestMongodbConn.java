package com.ai.wxy.springboot.mongodb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoDatabase;

public class TestMongodbConn{
    public static void main(String[] args){
        String userName = "tpuser";
        String database = "tpmongodb";
        String password = "123456";
        String host  ="192.168.30.216";
        int    port = 27017;
        String uri = "mongodb://192.168.30.216:27017/tpmongodb";
        MongoCredential  credential = MongoCredential.createPlainCredential(userName, database, password.toCharArray());
        MongoClientOptions options  = MongoClientOptions.builder().build();
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        try{
//            MongoClient mongoClient = new MongoClient(Collections.singletonList(new ServerAddress(host, port)),credentials, options);
            MongoClient mongoClient = new MongoClient(new MongoClientURI(uri, builder(options)));
            System.out.println("--------------------->"+mongoClient);
            MongoDatabase  db = mongoClient.getDatabase(database);
            
            if(db!=null){
                db.createCollection("test");
                
                Document doc = new Document();
                doc.put("test", 1);
                doc.put("age", 10);
                db.getCollection("test").insertOne(doc);
            }else{
                System.out.println("error---->");
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static Builder builder(MongoClientOptions options) {
        if (options != null) {
            return MongoClientOptions.builder(options);
        }
        return MongoClientOptions.builder();
    }
}
