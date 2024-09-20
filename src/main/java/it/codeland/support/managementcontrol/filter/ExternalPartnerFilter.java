package it.codeland.support.managementcontrol.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalPartnerFilter extends ErpFilter {
    String key;
    Boolean active;
}
