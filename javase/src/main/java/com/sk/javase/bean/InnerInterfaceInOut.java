package com.sk.javase.bean;

/**
 * @Title: InnerInterfaceInOut
 * @Package: com.sk.javase.bean
 * @Description:
 * @Author: sk
 * @Date: 2021/8/30 - 11:35
 */
public class InnerInterfaceInOut {
    public interface Fly{

        /**
         * 抽象方法
         */
        void doFly();
    }
}

class FlyImpl implements InnerInterfaceInOut.Fly{

    @Override
    public void doFly() {

    }
}
