package com.currency.ly.j2se.design.decorate;

/**
 * 饮料抽象基类:
 * 最近大街上流行起来了“快乐柠檬”，我们把快乐柠檬的饮料分为三类：红茶、绿茶、咖啡，在这三大类的基础上，又增加了许多的口味，什么金桔柠檬红茶、
 * 金桔柠檬珍珠绿茶、芒果红茶、芒果绿茶、芒果珍珠红茶、烤珍珠红茶、烤珍珠芒果绿茶、椰香胚芽咖啡、焦糖可可咖啡等等，每家店都有很长的菜单，但是仔细看下，
 * 其实原料也没几样，但是可以搭配出很多组合，如果顾客需要，很多没出现在菜单中的饮料他们也是可以做的。
 * <p>
 * 在这个例子中，红茶、绿茶、咖啡是最基础的饮料，其他的像金桔柠檬、芒果、珍珠、椰果、焦糖等都属于装饰用的。当然，在开发中，
 * 我们确实可以像门店一样，开发这些类：LemonBlackTea、LemonGreenTea、MangoBlackTea、MangoLemonGreenTea......但是，
 * 很快我们就发现，这样子干肯定是不行的，这会导致我们需要组合出所有的可能，而且如果客人需要在红茶中加双份柠檬怎么办？三份柠檬怎么办
 * -----------------------------------
 * 万字图文详解24种设计模式
 * https://blog.51cto.com/u_14987832/4807265
 *
 * @Author wuliangyong
 * @Date 2022/8/8 10:52
 */
public abstract class Beverage {

    // 返回描述
    public abstract String getDescription();

    // 返回价格
    public abstract double cost();

    //然后是三个基础饮料实现类，红茶、绿茶和咖啡：
    public class BlackTea extends Beverage {
        @Override
        public String getDescription() {
            return "红茶";
        }

        @Override
        public double cost() {
            return 10;
        }
    }

    public static class GreenTea extends Beverage {
        @Override
        public String getDescription() {
            return "绿茶";
        }

        @Override
        public double cost() {
            return 11;
        }
    }
    //定义调料，也就是装饰者的基类，此类必须继承自 Beverage：

    public static abstract class Condiment extends Beverage {

    }

    //然后我们来定义柠檬、芒果等具体的调料，它们属于装饰者，毫无疑问，这些调料肯定都需要继承调料 Condiment 类
    public static class Lemon extends Condiment {
        private Beverage bevarage;

        // 这里很关键，需要传入具体的饮料，如需要传入没有被装饰的红茶或绿茶，
        // 当然也可以传入已经装饰好的芒果绿茶，这样可以做芒果柠檬绿茶
        public Lemon(Beverage bevarage) {
            this.bevarage = bevarage;
        }

        @Override
        public String getDescription() {
            // 装饰
            return bevarage.getDescription() + ", 加柠檬";
        }

        @Override
        public double cost() {
            // 装饰
            return bevarage.cost() + 2; // 加柠檬需要 2 元
        }
    }

    public static class Mango extends Condiment {
        private Beverage bevarage;

        public Mango(Beverage bevarage) {
            this.bevarage = bevarage;
        }

        @Override
        public String getDescription() {
            return bevarage.getDescription() + ", 加芒果";
        }

        @Override
        public double cost() {
            return bevarage.cost() + 3; // 加芒果需要 3 元
        }
    }

    //看客户端调用
    public static void main(String[] args) {
        // 首先，我们需要一个基础饮料，红茶、绿茶或咖啡
        Beverage beverage = new GreenTea();
        // 开始装饰
        beverage = new Lemon(beverage); // 先加一份柠檬
        beverage = new Mango(beverage); // 再加一份芒果

        System.out.println(beverage.getDescription() + " 价格：￥" + beverage.cost());
        //"绿茶, 加柠檬, 加芒果 价格：￥16"
    }

}

