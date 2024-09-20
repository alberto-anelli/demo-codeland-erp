package com.example.demo.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ErpPaginationData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int number;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private int size;
    private boolean first;
    private boolean last;

    public static final ErpPaginationData fromPage(Page<?> page) {
        ErpPaginationData pagination = new ErpPaginationData();
        pagination.setNumber(page.getNumber());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setNumberOfElements(page.getNumberOfElements());
        pagination.setTotalElements(page.getTotalElements());
        pagination.setSize(page.getSize());
        pagination.setFirst(page.isFirst());
        pagination.setLast(page.isLast());
        return pagination;
    }
}
