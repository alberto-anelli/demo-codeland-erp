package it.codeland.support.managementcontrol.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollaboratorFilter extends ManagementControlFilter {
    Boolean active;
    Long idJobRole;
    Long idCompany;
}
