package com.chuyeu.training.myapp.dao.api;

import java.util.List;

public interface AbstractDao <T, I, U> {

	List<T> getAll();

    T get(Integer id);

    I insert(T entity);

    U update(T entity);

    void delete(Integer id);
}
