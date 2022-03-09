package cn.wyedward.core.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自己写的分页器对象
 * @param <T> 返回结果T类
 */
@Data
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 6011076384284780151L;
    private int currPage; //当前页数
    private int pageSize; //每页显示的个数
    private int start;
    private int end;
    //private long total; //总记录数 需要从后台查询条数然后返回给前台
    //private List<T> result; //分页查询的结果
    public PageBean(){}
    public PageBean(int currPage, int pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.start = (currPage-1) *pageSize;
        this.end = pageSize; //(currPage) * pageSize
    }
}
