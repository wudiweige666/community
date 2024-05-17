package com.nowcoder.community.controller;
//controller负责处理浏览器请求，然后根据请求调用业务组件service
import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    //将service注入controller
    @Autowired
    private AlphaService alphaService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello spring boot";
    }
    //注解声明，注意更改路径
    @ResponseBody
    @RequestMapping("/data")
    public String getData(){
        return alphaService.find();
    }

    //底层的方式处理请求响应
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取第一行请求行数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //访问消息头Header,下面方法返回的是迭代器
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        //获取消息体
        System.out.println(request.getParameter("code"));
        //返回响应数据
        //返回响应数据类型，如返回文本网页
        response.setContentType("text/html;charset=utf-8");
        //获取输出流
        //这种写法的新的Java写法，在方法使用结束后会自动调用close方法关闭输出流
        try (PrintWriter writer = response.getWriter();
        ){
            //向浏览器打印网页
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //封装后的处理请求响应更简单的方式
    //Get方法  ,get请求在明面的路径上，而且有长度限制
    //如获取students表格中参数为current=20和limit=13的数据：/students?current=20&&limit=13
    //明确处理请求的方法，防止漏判
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    //有时候所要的参数可能没有，则可以采用参数注入进行更详细的声明，required = false表示可以不传入参数此时会用默认值
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "12") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "09") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    //根据id只获取一个学生的信息,注意添加path，id要加{}
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //Post请求，浏览器向服务器发送信息
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    //响应字符串，这里的参数名与html文件中表单信息一致就可以访问到
    public String saveStudent(String name,String age){
        System.out.println(name);
        System.out.println(age);
        return "提交成功";
    }
    //响应html数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    //不加@ResponseBody则自动默认返回HTML
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",26);
        //由于动态网页存在templates中，会自动认为添加的是HTML文件所以下面相当于添加的是view.html
        mav.setViewName("/demo/view");
        return mav;
    }
    //另一种稍微简单的方式实现
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    //将model作为参数
    public String getSchool(Model model){
        model.addAttribute("name","太原理工大学");
        model.addAttribute("age",120);
        //将view.html页面路径作为返回值
        return "/demo/view";
    }

    //服务器向浏览器响应JSON数据（当异步请求时，即当前页面不刷新却访问服务器进行准确性判断）
    //Java对象 -> JSON对象（是固定格式的字符串） -> JS对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    //必须要有，否则会认为返回网页
    @ResponseBody
    //当dispatcherServlet扫描到这时，会将下面的Map转为JSON格式
    public Map<String,Object> getEmp(){
        Map<String ,Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",35);
        emp.put("salary",12000);
        return emp;
    }
    //若果返回多组值
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String ,Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",35);
        emp.put("salary",12000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age",55);
        emp.put("salary",15000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","赵六");
        emp.put("age",25);
        emp.put("salary",10000);
        list.add(emp);

        return list;
    }


}
