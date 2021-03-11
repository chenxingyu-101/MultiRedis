package org.HUST.CXY.Database;

import java.util.concurrent.ConcurrentHashMap;

public class MutilRedisDb {
    private int id;
    public ConcurrentHashMap<String,String> dict;
    public MutilRedisDb() {
        dict=new ConcurrentHashMap<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}