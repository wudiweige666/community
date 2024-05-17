package com.nowcoder.community.service;
//业务代码，会调用dao访问数据库
import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//默认singleton单例模式，若想实现多例，即每次访问bean都创建一个新的实例
//@Scope("prototype")//一般不需要

public class AlphaService {
    //将dao注入service
    @Autowired
    private AlphaDao alphaDao;

    //用于对比，判断下面的函数是在构造之后调用
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    //在构造函数之后调用
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }
    //销毁函数,在销毁之前被调用
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
