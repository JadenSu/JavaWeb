package jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
Xpath查询语句练习
 */
public class XpathDemo {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //1.获取path
        String path = XpathDemo.class.getClassLoader().getResource("student.xml").getPath();
        //2.获取document对象  使用Jsoup工具parse方法获取对象 parse(file,"编码格式")
        Document document = Jsoup.parse(new File(path), "utf-8");
        //3.!!根据doucment对象创建JXDocument对象  直接new
        JXDocument jxDocument = new JXDocument(document);
        //4.1查询所有student标签
        List<JXNode> jxNodes = jxDocument.selN("//student");
        //4.2查询所有student标签下的name标签
        List<JXNode> jxNodes1 = jxDocument.selN("//student/name");
        //4.3查询student标签下带有id属性的name标签
        List<JXNode> jxNodes2 = jxDocument.selN("//student/name[@id]");
        //4.4查询student标签下带有id属性的name标签 并且id属性值为itcast
        List<JXNode> jxNodes3 = jxDocument.selN("student/name[@id = 'itcast']");
    }
}
