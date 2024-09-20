package it.codeland.support.managementcontrol.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ErpPageData<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ErpPaginationData pagination;
    private transient List<T> items;

    public static final <T> ErpPageData<T> fromPage(Page<T> page) {
        Objects.requireNonNull(page, "page");
        ErpPageData<T> resultSet = new ErpPageData<>();
        resultSet.setPagination(ErpPaginationData.fromPage(page));
        resultSet.setItems(page.getContent());
        return resultSet;
    }
}
