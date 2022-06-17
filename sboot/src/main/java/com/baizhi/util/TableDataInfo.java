package com.baizhi.util;

import com.baizhi.constant.AjaxResult;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author gmz
 */
public class TableDataInfo extends AjaxResult {
    private static final long serialVersionUID = 5990135366464207824L;



    /**
     * 分页数据对象
     */
    public static final String ROWS_TAG = "rows";



    /**
     * 分页数据总条数
     */
    public static final String TOTAL_TAG = "total";


    /**
     * 分页数据总条数
     */
    public static final String status = "status";

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
        super();
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        super();
        put(ROWS_TAG, list);
        put(TOTAL_TAG, total);
    }

    public void setTotal(long total) {
        put(TOTAL_TAG, total);
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public void setRows(List<?> rows) {
        put(ROWS_TAG, rows);
    }

    public void setCode(HttpStatus httpStatus) {
        put(CODE_TAG, httpStatus.value());
    }
}