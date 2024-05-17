package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//查询后由于有个外键userId,在页面上不会直接显示该id而是其对应的名字
//方法1：写sql时进行关联查询用户去处理，将用户的数据一起查到
//方法2：单独的处理DiscussPost将查询到的user与DiscussPost组合到一起，返回给页面。需要再提供一个方法，能根据userId查到用户，在UserService中实现
//选第二种，将来使用redis缓存数据时更方便，性能高，代码直观

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
