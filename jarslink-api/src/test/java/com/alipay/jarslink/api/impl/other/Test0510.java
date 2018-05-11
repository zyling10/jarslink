package com.alipay.jarslink.api.impl.other;

import com.google.common.primitives.Ints;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author LINGZHIYONG764
 * @date 2018/5/10 11:36
 */
public class Test0510 {
    public static void main(String[] args) {

        /*
        java中System.nanoTime()返回的是纳秒，nanoTime而返回的可能是任意时间，
        甚至可能是负数……按照API的说明，nanoTime主要的用途是衡量一个时间段，
        比如说一段代码执行所 用的时间，获取数据库连接所用的时间，网络访问所用的时间等。
        另外，nanoTime提供了纳秒级别的精度，但实际上获得的值可能没有精确到纳秒。
        */

        long t0 = System.nanoTime();
        long t1 = System.nanoTime();
        System.out.println(t1-t0);

//        int capacity = Maps.capacity(2);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Ints.MAX_POWER_OF_TWO);
        System.out.println(Ints.MAX_POWER_OF_TWO *2-1);
        int expectedSize = 5;
        System.out.println(expectedSize + expectedSize / 3);
        String className = null;
        checkNotNull(className, "className is null");
    }
}
