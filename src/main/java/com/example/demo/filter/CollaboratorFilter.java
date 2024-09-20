package com.example.demo.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollaboratorFilter extends ErpFilter {
    String key;
    Boolean active;
    Long idJobRole;
    Long idCompany;
}
