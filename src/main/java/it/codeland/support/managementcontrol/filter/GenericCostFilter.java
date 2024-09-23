package it.codeland.support.managementcontrol.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericCostFilter extends ManagementControlFilter {
    Long idArea;
    String year;
}
