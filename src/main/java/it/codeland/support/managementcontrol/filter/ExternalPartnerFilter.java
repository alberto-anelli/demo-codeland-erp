package it.codeland.support.managementcontrol.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalPartnerFilter extends ManagementControlFilter {
    String key;
    Boolean active;
}
