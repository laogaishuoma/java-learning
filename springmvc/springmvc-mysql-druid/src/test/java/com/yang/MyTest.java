package com.yang;

import com.yang.pojo.User;
import com.yang.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        UserService userServiceTmpl = (UserService) context.getBean("userServiceTmpl");

        for (User user : userServiceTmpl.findAll()) {
            System.out.println(user);
        }
    }
}
