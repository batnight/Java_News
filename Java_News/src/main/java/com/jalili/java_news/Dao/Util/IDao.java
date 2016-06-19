
package com.jalili.java_news.Dao.Util;

import java.util.List;


public interface IDao<T> {
     public int save(T t);
     public int update(T t);
     public int delete(T t);
     public List<T> selectAll();
     public T selectSingleById(T t);
}
