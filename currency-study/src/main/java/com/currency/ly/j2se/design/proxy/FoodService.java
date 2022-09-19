package com.currency.ly.j2se.design.proxy;

/**
 * 我们发现没有，代理模式说白了就是做 “方法包装” 或做 “方法增强”。在面向切面编程中，其实就是动态代理的过程。比如 Spring 中，我们自己不定义代理类，但是 Spring 会帮我们动态来定义代理，然后把我们定义在 @Before、@After、@Around 中的代码逻辑动态添加到代理中。
 *
 * 说到动态代理，又可以展开说，Spring 中实现动态代理有两种，一种是如果我们的类定义了接口，如 UserService 接口和 UserServiceImpl 实现，那么采用 JDK 的动态代理，感兴趣的读者可以去看看 java.lang.reflect.Proxy 类的源码；另一种是我们自己没有定义接口的，Spring 会采用 CGLIB 进行动态代理，它是一个 jar 包，性能还不错
 * -----------------------------------
 * 万字图文详解24种设计模式
 * https://blog.51cto.com/u_14987832/4807265
 * @Author wuliangyong
 * @Date 2022/8/8 16:30
 */
public interface FoodService {

    void makeChicken();

    void makeNoodle();

    public static class FoodServiceImpl implements FoodService {
        @Override
        public void makeChicken() {

        }

        @Override
        public void makeNoodle() {

        }
    }

    // 代理要表现得“就像是”真实实现类，所以需要实现 FoodService
    public class FoodServiceProxy implements FoodService {

        // 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
        private FoodService foodService = new FoodServiceImpl();

        @Override
        public void makeChicken() {
            System.out.println("我们马上要开始制作鸡肉了");

            // 如果我们定义这句为核心代码的话，那么，核心代码是真实实现类做的，
            // 代理只是在核心代码前后做些“无足轻重”的事情
            foodService.makeChicken();

            System.out.println("鸡肉制作完成啦，加点胡椒粉"); // 增强

        }

        @Override
        public void makeNoodle() {
            System.out.println("准备制作拉面~");
            foodService.makeNoodle();
            System.out.println("制作完成啦");

        }
    }

    public static void main(String[] args) {
        // 这里用代理类来实例化
        FoodService foodService = new FoodServiceProxy();
        foodService.makeChicken();
    }
}
