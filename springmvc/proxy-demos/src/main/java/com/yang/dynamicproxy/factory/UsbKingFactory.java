package com.yang.dynamicproxy.factory;

import com.yang.staticproxy.service.UsbSell;

/**
 * 目标类: 金士顿厂家
 */
public class UsbKingFactory implements UsbSell {
    @Override
    public float sell(int amount) {
        /**
         * 一个128G的U盘是85元
         * 后期根据amount可以实现不同的数量，单价有阶梯优惠
         */
        System.out.println("目标方法执行");
        return 85f;
    }
}
