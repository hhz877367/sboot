package com.baizhi.controller;


import com.baizhi.constant.StringUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 分页数据
 *
 * @author gmz
 */
@Data
public class PageDomain {
    private static final String DESC = "-";
    private static final String ASC = "+";
    /**
     * 当前记录起始索引
     */
    private Integer pageNum;
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
    /**
     * 排序列 c1,c2,c3
     */
    private String orderByColumn;
    /**
     * s1,s2,s3
     * 排序的方向 "desc" 或者 "asc".
     */
    private String isAsc;

    /**
     * 当前记录起始索引
     */
    private Integer offset;
    /**
     * 每页显示记录数
     */
    private Integer limit;
    /**
     * s1,-s2,+s3
     * 排序字段
     */
    private String sortBy;

    public String parseOrderBy() {
        String orderBy = "";
        if (StringUtils.isEmpty(sortBy)) {
            if (StringUtils.isNotEmpty(orderByColumn) && StringUtils.isNotEmpty(isAsc)) {
                String[] orderByColumns = orderByColumn.trim().split(",");
                String[] ascs = isAsc.trim().split(",");
                if (orderByColumns.length != ascs.length) {
                    throw new RuntimeException();
                }

                orderBy = IntStream.range(0, orderByColumns.length).mapToObj(e -> String
                        .format("%s %s",
                                StringUtils
                                        .toUnderScoreCase(
                                                orderByColumns[e]
                                                        .trim()),
                                ascs[e]
                                        .trim()))
                        .collect(Collectors.joining(","));

            }
        } else {
            orderBy = Arrays.stream(sortBy.split(",")).map(s -> {
                if (s.startsWith(DESC)) {
                    return String.format("%s %s", s.substring(1), "desc");
                } else {
                    if (s.startsWith(ASC)) {
                        return String.format("%s %s", s.substring(1).toUpperCase(), "asc");
                    } else {
                        return String.format("%s %s", s.toUpperCase(), "asc");
                    }
                }
            }).collect(Collectors.joining(","));
        }

        return orderBy;
    }
}
