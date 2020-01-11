package com.anton.expo.dao;

import java.util.List;

public interface Dao<T> {
    T get(long id);
    List<T> getAll();
    long save(T t);
    void update(T t);
    void delete(T t);
}
