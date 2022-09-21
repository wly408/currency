package com.currency.ly.j2ee.base.grammar;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author wuliangyong
 * @Date 2022/9/21 10:28
 */
@Data
public class GrammarTest {
    //transient:被transient修饰的变量不参与序列化和反序列化
    private transient String name;
    //线程在每次使用变量的时候，都会读取变量修改后的最的值。volatile很容易被误用，用来进行原子性操作。
    //通过标记flag来控制VolatileTest线程while循环退出的例子!:https://www.cnblogs.com/xd502djj/p/9873067.html
    private volatile Integer value;


    public static void main(String[] args) {
        GrammarTest grammarTest = new GrammarTest();
        grammarTest.setName("ly");
        grammarTest.setValue(1);
        System.out.println(JSONObject.toJSONString(grammarTest));
    }
}
