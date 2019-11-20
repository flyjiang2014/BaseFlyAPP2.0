package com.base.flyjiang.baseflyapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 */
public class DataPage<T> implements Serializable {

    public String totalCount, starNumAvg,average;//逗逼返回格式各种返回

    private int page ; // 每页的记录数

    private String totalNum="";

    private int more = 1; // 当前页第一条数据在List中的位置,从0开始

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    private List<T> items = new ArrayList() {
    }; // 当前页中存放的记录,类型一般为List
    public ArrayList<T> firstFiveList = new ArrayList<T>();

    public ArrayList<T>myBidList=new ArrayList<T>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}