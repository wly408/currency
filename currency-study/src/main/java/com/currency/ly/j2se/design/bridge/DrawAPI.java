package com.currency.ly.j2se.design.bridge;

/**
 *
 * 好像很好用
 *理解桥梁模式，其实就是理解代码抽象和解耦。
 *
 * @Author wuliangyong
 * @Date 2022/8/9 9:47
 */

public interface DrawAPI {
    //我们首先需要一个桥梁，它是一个接口，定义提供的接口方法。
     void draw(int radius, int x, int y);
    //然后是一系列实现类
    public class RedPen implements DrawAPI {
        @Override
        public void draw(int radius, int x, int y) {
            System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
        }
    }
    public class GreenPen implements DrawAPI {
        @Override
        public void draw(int radius, int x, int y) {
            System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
        }
    }
    public class BluePen implements DrawAPI {
        @Override
        public void draw(int radius, int x, int y) {
            System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
        }
    }
    //定义一个抽象类，此类的实现类都需要使用 DrawAPI：
    public abstract class Shape {
        protected DrawAPI drawAPI;
        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }

        public abstract void draw();
    }
   // 定义抽象类的子类：


    // 圆形
    public class Circle extends Shape {
        private int radius;
        public Circle(int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.radius = radius;
        }
        @Override
        public void draw() {
            drawAPI.draw(radius, 0, 0);
        }
    }
    // 长方形
    public class Rectangle extends Shape {
        private int x;
        private int y;

        public Rectangle(int x, int y, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
        }

        @Override
        public void draw() {
            drawAPI.draw(0, x, y);
        }
    }
    public static void main(String[] args) {
        //最后，我们来看客户端演示：
        Shape greenCircle = new Circle(10, new GreenPen());
        Shape redRectangle = new Rectangle(4, 8, new RedPen());
        greenCircle.draw();
        redRectangle.draw();
    }

}
