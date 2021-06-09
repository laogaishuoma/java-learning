package com.yang.staticproxy.service;

/**
 * 表示功能的: 厂家和商家都需要完成的功能
 */
public interface UsbSell {
    /**
     * 定义方法
     * @param amount: 表示一次买的数量，这里先不使用，仅做简单演示
     * @return
     */
    float sell(int amount);
}
