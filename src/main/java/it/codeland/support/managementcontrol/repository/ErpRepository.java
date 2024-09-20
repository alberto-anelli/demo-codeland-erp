package com.example.demo.repository;

import com.example.demo.filter.ErpFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ErpRepository<E> {
    default Pageable pageable(ErpFilter filter) {
        if(filter == null || filter.getPagination() == null) {
            return PageRequest.of(0, 6, Sort.by(Sort.Direction.ASC, "id"));
        }
        if(filter.getPagination().getSort() == null || (filter.getPagination().getSort().getDirection() == null
                && filter.getPagination().getSort().getProperties() == null)) {
            return PageRequest.of(filter.getPagination().getPageNumber(),
                    filter.getPagination().getPageSize(),
                    Sort.by(Sort.Direction.ASC, "id"));
        }
        if(filter.getPagination().getSort().getDirection() == null) {
            return PageRequest.of(filter.getPagination().getPageNumber(),
                    filter.getPagination().getPageSize(),
                    Sort.by(Sort.Direction.ASC, filter.getPagination().getSort().getProperties()));
        }
        if(filter.getPagination().getSort().getProperties() == null) {
            return PageRequest.of(filter.getPagination().getPageNumber(),
                    filter.getPagination().getPageSize(),
                    Sort.by(filter.getPagination().getSort().getDirection(), "id"));
        }
        return PageRequest.of(
                filter.getPagination().getPageNumber(),
                filter.getPagination().getPageSize(),
                Sort.by(filter.getPagination().getSort().getDirection(),
                        filter.getPagination().getSort().getProperties())
        );
    }
}
