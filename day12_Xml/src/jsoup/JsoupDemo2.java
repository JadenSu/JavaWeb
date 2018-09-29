package jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Jsoup对象
 */
public class JsoupDemo2 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = jsoupDemo.class.getClassLoader().getResource("student.xml").getPath();
        //解析文档，加载进内存,获取dom树
        Document document = Jsoup.parse(new File(path), "utf-8");
        //System.out.println(document);

        Elements element2 = document.getElementsByAttributeValue("number", "heima_0001");
        System.out.println(element2);

        System.out.println("===============");
        String number = element2.attr("number");
        System.out.println(number);


        System.out.println("=========选择器练习===========");
        //获取 number属性值为heima_0001的student标签  的  age标签
        //获取student标签
        Elements student = document.select("student[number='heima_0001']");
        System.out.println(student);
        //获取age子标签  选择器和css一样
        Elements age = document.select("student[number='heima_0001']>age");
        System.out.println(age);

        //根据选择器找到的标签其实是一个集合  使用Xpath确认位置
        System.out.println("===========xpath练习=======================");
        //1根据document对象创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);
        //2.结合xpath语法查询
        //    //studetnt 选取所有不管位置
        List<JXNode> jxNodes = jxDocument.selN("//student");
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode);
        }
        System.out.println("name标签");
        //查询student标签下的所有name标签  //student/name
        List<JXNode> jxNodes1 = jxDocument.selN("//student/name");
        for (JXNode jxNode : jxNodes1) {
            System.out.println(jxNode);
        }

        //查询带有id 属性的name标签  //student/name[@id]


        //查询 id为 itcast 的标签 //student/name[@id='itcast']

    }
}
