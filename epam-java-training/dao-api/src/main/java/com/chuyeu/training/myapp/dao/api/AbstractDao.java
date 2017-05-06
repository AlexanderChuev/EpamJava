package com.chuyeu.training.myapp.dao.api;

public interface AbstractDao <T> {

    T get(Integer id);
    void delete(Integer id);
}
