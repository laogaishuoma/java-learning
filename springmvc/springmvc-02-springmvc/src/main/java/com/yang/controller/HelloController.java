package com.yang.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注意: 这里我们先导入Controller接口
 */
public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // 1. 实例化ModelAndView           模型和视图
        ModelAndView mv = new ModelAndView();

        // 2. 封装对象，放在ModelAndView中。 Model
        mv.addObject("msg","HelloSpringMVC!");

        // 3. 封装要跳转的视图，放在ModelAndView中: /WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");
        return mv;
    }
}
