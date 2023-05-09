package com.example.music.dal;

import java.util.List;

public interface IDao<T> {
    /**
     * 获取数据
     * @return 数据的List集合
     */
    List<T> getData();
}
