package com.yang.staticproxy.merchant;

import com.yang.staticproxy.factory.UsbKingFactory;
import com.yang.staticproxy.service.UsbSell;

public class Weishang implements UsbSell {
    /**
     * 声明 商家代理的厂家具体是谁
     */
    private UsbKingFactory factory = new UsbKingFactory();

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
        price += 1;

        // 在目标类的方法调用后，你做的其他功能都是增强的意思。

        System.out.println("微商的增强功能");
        return price;
    }
}
