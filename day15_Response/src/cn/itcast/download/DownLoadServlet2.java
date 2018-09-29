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

@WebServlet(name = "DownLoadServlet2")
public class DownLoadServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //下载 跳出提示
        //获取文件名  request获取而不是response
        String filename = request.getParameter("filename");
        //将文件加载进内存
        ServletContext servletContext = getServletContext();
        //获取绝对路径!!  需要知道文件所在位置  如果在web目录下直接文件名   src目录下的需要再classes目录下查找
        String realPath = servletContext.getRealPath("/img/"+filename);
        //字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //设置响应头  让链接点击后跳出下载附件的框框
        //设置文件类型
        //获得文件类型
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType);
        //设置文件打开方式
        response.setHeader("content-dipositon","attachment;filename="+filename);
        //输出流
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fis.read(bytes))!=-1){
            sos.write(bytes);
        }
        sos.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
