package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//使用Primary可以在调用接口的类时在多个类中优先调用当前类
@Repository
@Primary
public class AlphaMybatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "Mybatis";
    }
}
