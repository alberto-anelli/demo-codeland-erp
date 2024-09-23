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
public class ManagementControlPageData<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ErpPaginationData pagination;
    private transient List<T> items;

    public static final <T> ManagementControlPageData<T> fromPage(Page<T> page) {
        Objects.requireNonNull(page, "page");
        ManagementControlPageData<T> resultSet = new ManagementControlPageData<>();
        resultSet.setPagination(ErpPaginationData.fromPage(page));
        resultSet.setItems(page.getContent());
        return resultSet;
    }
}
