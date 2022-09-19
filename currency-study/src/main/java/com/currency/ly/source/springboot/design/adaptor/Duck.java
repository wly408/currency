package com.currency.ly.source.springboot.design.adaptor;

/**
 * 1.awt中的适配器
 * 2.无非是我们需要一只鸭，但是我们只有一只鸡，这个时候就需要定义一个适配器，由这个适配器来充当鸭，但是适配器里面的方法还是由鸡来实现的。
 * @Author wuliangyong
 * @Date 2022/8/9 9:42
 */
public interface Duck {
     void quack(); // 鸭的呱呱叫


    public static interface Cock {
         void gobble(); // 鸡的咕咕叫

    }
    public class WildCock implements Cock {
        @Override
        public void gobble() {
            System.out.println("咕咕叫");
        }

    }
    // 毫无疑问，首先，这个适配器肯定需要 implements Duck，这样才能当做鸭来用
    public class CockAdapter implements Duck {

        Cock cock;
        // 构造方法中需要一个鸡的实例，此类就是将这只鸡适配成鸭来用
        public CockAdapter(Cock cock) {
            this.cock = cock;
        }

        // 实现鸭的呱呱叫方法
        @Override
        public void quack() {
            // 内部其实是一只鸡的咕咕叫
            cock.gobble();
        }

    }



}
