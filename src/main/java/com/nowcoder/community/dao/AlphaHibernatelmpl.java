package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

//加注解为了能让spring扫描到本bean
//@Repository
//给类取了一个新名字，在类名长的时候使用，这样在调用类时可以通过类的别名访问
@Repository("alphaHibernate")
public class AlphaHibernatelmpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
