package cn.itcast.domain;

import java.util.List;

/**
 * 分页对象
 * 需要的属性
 * 1.总记录数
 * 2.总页数
 * 3.每页记录数
 * 4.当前页码
 * 5.每页的数据
 */
public class PageBean<T> {


    private int totalCount;     //总记录数      数据库
    private int totalPage;      //总页数        计算
    private int rows;           //每页记录数    获取---   改成不从页面获取 直接设置
    private int currentPage;    //当前页码      获取  !!
    private List<T> list;       //每页数据      数据库

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", rows=" + rows +
                ", currentPage=" + currentPage +
                ", list=" + list +
                '}';
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
