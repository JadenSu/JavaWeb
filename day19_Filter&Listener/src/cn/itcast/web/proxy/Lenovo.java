package cn.itcast.web.proxy;

/**
 * 真实类
 */
public class Lenovo implements SaleComputer{
    @Override
    public String sale(double money) {
        System.out.println("花了"+money+"买美帝良心");
        return "美帝良心";
    }

    @Override
    public void show() {
        System.out.println("展示国内特产加价削配置电脑");
    }
}
