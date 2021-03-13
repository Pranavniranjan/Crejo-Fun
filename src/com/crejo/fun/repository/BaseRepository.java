package com.crejo.fun.repository;

import java.util.List;

public abstract class BaseRepository {

    public void save(Object object, List<Object> objectList){
        objectList.add(object);
    }
}
