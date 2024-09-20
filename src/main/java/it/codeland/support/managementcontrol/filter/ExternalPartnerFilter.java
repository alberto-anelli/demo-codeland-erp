package com.example.demo.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class ExternalPartnerFilter extends ErpFilter {
    String key;
    Boolean active;
}
