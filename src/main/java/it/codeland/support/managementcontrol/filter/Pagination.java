package it.codeland.support.managementcontrol.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pagination {
    int pageNumber;
    int pageSize;
    Sort sort;
}
