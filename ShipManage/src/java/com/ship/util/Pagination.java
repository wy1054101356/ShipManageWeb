/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.util;

/**
 * 用来封装分页信息
 */
public class Pagination {

    private int pageNo = 1; //当前页码
    private int pageCount = 0; //总页数
    private int pageSize = 8; //每页显示记录数
    private int countSize = 0; //总记录条数

    private String pageBar;
    private String numPageBar;
    private String url;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        if (countSize != 0) {
            if (countSize % pageSize != 0) {
                pageCount = countSize / pageSize + 1;
            } else {
                pageCount = countSize / pageSize;
            }
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCountSize() {
        return countSize;
    }

    public void setCountSize(int countSize) {
        this.countSize = countSize;
    }

    public String getPageBar() {
        pageCount = getPageCount();

        if (pageCount != 0) {
            //判断当前页号的合法性
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageCount) {
                pageNo = pageCount;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("每页：").append(pageSize).append("&nbsp;&nbsp;页次：").append(pageNo).
                    append("/").append(pageCount).append("&nbsp;&nbsp;总计：").append(countSize).
                    append("&nbsp;");
            sb.append("<ul class=\"pagination pagination-small border-main\">");
            if (pageNo == 1) {
                sb.append("<li class=\"disabled\"><a href=\"#\">首页</a> </li>");
                sb.append("<li class=\"disabled\"><a href=\"#\">上页</a> </li>");
            } else {
                sb.append("<li><a href=\"").append(url).append("&pageNo=").append("1\">首页</a> </li>");
                sb.append("<li><a href=\"").append(url).append("&pageNo=").append(pageNo - 1).append("\">上页</a> </li>");
            }

            if (pageNo == pageCount) {
                sb.append("<li class=\"disabled\"><a href=\"#\">下页</a> </li>");
                sb.append("<li class=\"disabled\"><a href=\"#\">尾页</a> </li>");
            } else {
                sb.append("<li><a href=\"").append(url).append("&pageNo=").append(pageNo + 1).append("\">下页</a> </li>");
                sb.append("<li><a href=\"").append(url).append("&pageNo=").append(pageCount).append("\">尾页</a> </li>");
            }
            sb.append("</ul>");
            pageBar = sb.toString();
        } else {
            pageBar = "";
        }
        return pageBar;
    }

    public String getNumPageBar() {
        pageCount = getPageCount();

        if (pageCount != 0) {
            //判断当前页号的合法性
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageCount) {
                pageNo = pageCount;
            }
            StringBuilder sb = new StringBuilder();
            //计算当前页所在的组
            int group = pageNo / 5 + (pageNo % 5 == 0 ? 0 : 1);
            int start = (group - 1) * 5 + 1;
            int end = start + 4;
            System.out.println("group:" + group + ";start:" + start + ";end:" + end);
            if (end > pageCount) {
                end = pageCount;
            }

            sb.append("<ul class=\"pagination pagination-small border-main\">");
            if (start > 1) {
                sb.append("<li><a href=\"").append(url).append("&pageNo=").
                        append(start - 1).append("\">&lt;</a></li>");
            }
            for (int i = start; i <= end; i++) {
                if (pageNo != i) {
                    sb.append("<li><a href=\"").append(url).append("&pageNo=").append(i).
                            append("\">").append(i).append("</a></li>");
                } else {
                    sb.append("<li class=\"active\"><a href=\"#\">").append(i).append("</a></li>");
                }
            }
            if (end < pageCount) {
                sb.append("<li><a href=\"").append(url).append("&pageNo=").append(end + 1).append("\">&gt;</a></li>");
            }
            sb.append("</ul>");
            numPageBar = sb.toString();
        } else {
            numPageBar = "";
        }
        return numPageBar;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
