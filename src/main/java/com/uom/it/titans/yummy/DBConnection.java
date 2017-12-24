package com.uom.it.titans.yummy;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by Chamath on 1/8/2017.
 */
public class DBConnection {

    DB mongoDB;

    private DBConnection(){
    }

    public DB getMongoDBConnection() throws UnknownHostException {
        if(mongoDB == null){
            initDB();
        }
        return mongoDB;
    }

    private synchronized void initDB() throws UnknownHostException {
        if(mongoDB == null) {
            MongoClient mongoClient = new MongoClient(Constants.host, Constants.port);
            mongoDB = mongoClient.getDB(Constants.dbName);
        }
    }

    private static class SingletonHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DB getConnection() throws UnknownHostException {
        return SingletonHolder.INSTANCE.getMongoDBConnection();
    }
}