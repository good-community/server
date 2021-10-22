package com.bupt.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils {
    public static <T, R> Page<R> convert(Page<T> oriPage, Function<T, R> mapper) {
        List<R> list = oriPage.getRecords().stream()
                .map(mapper)
                .collect(Collectors.toList());
        Page<R> page = new Page<>(oriPage.getCurrent(), oriPage.getSize(), oriPage.getTotal());
        page.setRecords(list);
        return page;
    }
}
