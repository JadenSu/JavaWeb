package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/*
selector查询语句练习
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //1.获取path
        String path = SelectorDemo.class.getClassLoader().getResource("student.xml").getPath();
        //2.获取document对象  使用Jsoup工具parse方法获取对象 parse(file,"编码格式")
        Document document = Jsoup.parse(new File(path), "utf-8");
        //3.查询name标签
        Elements es = document.getElementsByTag("name");
        System.out.println(es);
        System.out.println("========");
        //4.查询id值为itcast的元素
        Elements e1 = document.select("#itcast");
        System.out.println(e1);
        System.out.println("----------------");
        //5.获取student标签并且number属性值为heima_0001的age子标签
        Elements e2 = document.select("student[number='heima_0001'] > age");
        System.out.println(e2);
    }
}
