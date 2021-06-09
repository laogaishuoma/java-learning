package com.yang.staticproxy.client;

import com.yang.staticproxy.merchant.Taobao;
import com.yang.staticproxy.merchant.Weishang;

public class Customer {
    public static void main(String[] args) {
        Taobao taobao = new Taobao();
        float money = taobao.sell(1);
        System.out.println("我从淘宝买了一个金士顿U盘, 价格是: " + money);

        Weishang weishang = new Weishang();
        float money2 = weishang.sell(1);
        System.out.println("我从微商手里买了一个金士顿U盘, 价格是: " + money2);
    }
}
