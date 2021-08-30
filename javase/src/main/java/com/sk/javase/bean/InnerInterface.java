package com.sk.javase.bean;

/**
 * @Title: InnerInterface
 * @Package: com.sk.javase.bean
 * @Description:
 * @Author: sk
 * @Date: 2021/8/30 - 10:59
 */
public class InnerInterface {

    /**
     * 内部接口
     */
    public interface Fly{
        /**
         * 抽象方法
         */
        void doFly();
    }

    public class FlyImpl implements Fly{

        @Override
        public void doFly() {
            System.out.println("内部接口实现类");
        }
    }

    public static void main(String[] args) {
        InnerInterface innerInterface = new InnerInterface();
        Fly fly = innerInterface.new FlyImpl();

        fly.doFly();
    }
}
