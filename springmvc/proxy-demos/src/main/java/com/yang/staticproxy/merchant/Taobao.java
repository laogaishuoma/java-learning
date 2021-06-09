package com.yang.staticproxy.merchant;

import com.yang.staticproxy.factory.UsbKingFactory;
import com.yang.staticproxy.service.UsbSell;

/**
 * 淘宝是一个商家，代理金士顿U盘的销售
 */
public class Taobao implements UsbSell {
    /**
     * 声明 商家代理的厂家具体是谁
     */
    private UsbKingFactory factory = new UsbKingFactory();

    /**
     * 实现销售U盘功能
     * @param amount: 表示一次买的数量，这里先不使用，仅做简单演示
     * @return
     */
    @Override
    public float sell(int amount) {
        /**
         * 向商家发送订单，告诉厂家，我买了U盘，厂家发货
         * 得到商家的价格
         */
        float price = factory.sell(amount);

        /**
         * 商家要加价，也就是代理要增加价格
         */
        price += 25;

        // 在目标类的方法调用后，你做的其他功能都是增强的意思。

        System.out.println("淘宝商家，给你返一个优惠券或者红包");
        return price;
    }
}
