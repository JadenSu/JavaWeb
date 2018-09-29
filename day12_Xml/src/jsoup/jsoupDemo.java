package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class jsoupDemo {
    public static void main(String[] args) throws IOException {
        //获取路径
        String path = jsoupDemo.class.getClassLoader().getResource("student.xml").getPath();
        //解析文档，加载进内存,获取dom树
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象  是一个数组
        Elements elements = document.getElementsByTag("name");
        //获取name数组的第一个元素对象
        Element element = elements.get(0);
        //获取name元素的数据
        String name = element.text();
        System.out.println(name);


        //获取age元素
        Elements ageElements = document.getElementsByTag("age");
        //获取第一个
        Element ageElement = ageElements.get(0);
        String age = ageElement.text();
        System.out.println(age);
    }
}
