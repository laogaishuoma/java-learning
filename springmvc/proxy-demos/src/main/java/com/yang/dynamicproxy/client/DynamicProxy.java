package com.yang.dynamicproxy.client;

import com.yang.dynamicproxy.factory.UsbKingFactory;
import com.yang.dynamicproxy.handler.MyInvocationHandler;
import com.yang.staticproxy.service.UsbSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        
        /**
         * 1. 创建目标对象
         */
        UsbSell factory = new UsbKingFactory();

        /**
         * 2. 创建InvocationHandler对象
         */
        InvocationHandler handler = new MyInvocationHandler(factory);

        /**
         * 3. 创建代理对象
         */
        UsbSell proxy = (UsbSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(),
                handler);

        System.out.println("proxy: " + proxy.getClass().getName());
        /**
         * 4. 通过代理执行方法
         */
        float price = proxy.sell(1);
        System.out.println("通过动态代理对象，调用方法: " + price);
    }
}
