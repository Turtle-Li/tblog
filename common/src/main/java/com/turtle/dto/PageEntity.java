package com.turtle.dto;

import lombok.Value;

import java.util.List;
/**
 * @author lijiayu
 * @date 2020/6/2
 * @description
 */
@Value
public class PageEntity<T> {
    private final List<T> data;
    private final Long total;

    public PageEntity() {
        this.data = List.of();
        this.total = 0L;
    }

    public PageEntity(Long total, List<T> data) {
        this.data = data;
        this.total = total;
    }


    public static <T> PageEntity<T> of(int total, List<T> data) {
        return new PageEntity<>((long) total, data);
    }

    public static <T> PageEntity<T> of(long total, List<T> data) {
        return new PageEntity<>(total, data);
    }

    public static <T> PageEntity<T> of() {
        return new PageEntity<>();
    }
}
