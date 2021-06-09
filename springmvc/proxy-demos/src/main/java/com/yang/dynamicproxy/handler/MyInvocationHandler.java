package com.yang.dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target = null;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 向商家发送订单，告诉厂家，我买了U盘，厂家发货
         * 得到商家的价格
         */
        //float price = factory.sell(amount);
        Object res = null;

        res = method.invoke(target, args);

        /**
         * 商家要加价，也就是代理要增加价格
         */
        if (res != null) {
            Float price = (Float) res;
            price += 25;

            res = price;
        }


        // 在目标类的方法调用后，你做的其他功能都是增强的意思。

        System.out.println("淘宝商家，给你返一个优惠券或者红包");
        return res;
    }
}
