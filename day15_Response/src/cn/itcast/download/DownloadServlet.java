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

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，文件名称
        String filename = request.getParameter("filename");
        //2.使用字节输入流加载文件进内存
        //2.1找到文件服务器路径
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("/img/"+filename);//web目录下img文件夹内的文件
        //2.2用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //3.设置response的响应头
        //3.1设置响应头类型：content-type
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type",mimeType); //告诉浏览器响应体的数据量类型
        //3.2设置响应头打开方式:content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);
        // 4.将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fis.read(bytes))!=-1){
            sos.write(bytes,0,len);
        }
        sos.close();//fis会随着response的关闭而关闭

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
