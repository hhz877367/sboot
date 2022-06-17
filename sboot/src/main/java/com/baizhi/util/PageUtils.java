package com.baizhi.util;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @Author Crazy.X
 * @DATE 2020/2/13
 */
public class PageUtils {
    public static <T> Page<T> pageSetUp(Integer page, Integer limit) {
        return PageHelper.startPage(page == null ? 0 : page, limit == null ? 10 : limit);
    }

    public static <T> Page<T> pageSetUp(Integer page) {
        return PageHelper.startPage(page == null ? 0 : page, 1);
    }
}
