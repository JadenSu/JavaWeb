package cn.itcast.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 下载案例
 * 分析:
 * 1获取文件名
 * 2.字节流加载文件进内存   需要获取文件服务器的路径  路径和文件所在服务器位置有关src目录在classes文件夹获取
 * 获取servletContext对象  该对象呈现WEB应用servlet视图 通过该对象获取绝对路径
 * 3.字节流关联绝对路径
 * 4.需要弹出下载框  设置响应头  content-type  需要获取文件类型 mineType
 *                  设置相应头  content-disposition 设置箱体打开方式
 * 5.温江输出流 写出数据 记得关闭 输入流 输出流由reponse获取 不需关闭流
 *
 */
@WebServlet("/downloadServlet3")
public class DownloadServlet3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取文件名
        String filename = request.getParameter("filename");
        //2.获取绝对路径
        //2.1 获取 servletContent对象
        ServletContext servletContext = getServletContext();
        //2.2获取绝对路径  文件在 web/img文件夹内
        String realPath = servletContext.getRealPath("/img/" + filename);
        //2.3字符流关联绝对路径
        FileInputStream fis = new FileInputStream(realPath);
        //3设置2个响应头   文件类型  以及  响应体打开方式
        //3.1  获取文件类型
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //3.2 设置响应体打开方式
        response.setHeader("content-disposition","attachment;filename="+filename);
        //4.文件输出流
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fis.read(bytes))!=-1){
            sos.write(bytes,0,len);
        }
        fis.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
