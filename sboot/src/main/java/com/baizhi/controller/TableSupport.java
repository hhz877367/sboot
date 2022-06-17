package com.baizhi.controller;

import com.baizhi.util.ServletUtils;

/**
 * 表格数据处理
 *
 * @author gmz
 */
public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 当前记录起始索引
     */
    public static final String OFFSET = "offset";

    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段, s1,+s2,-s3
     */
    public static final String SORT_BY = "sortBy";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));

        pageDomain.setOffset(ServletUtils.getParameterToInt(OFFSET));
        pageDomain.setLimit(ServletUtils.getParameterToInt(LIMIT));
        pageDomain.setSortBy(ServletUtils.getParameter(SORT_BY));

        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}