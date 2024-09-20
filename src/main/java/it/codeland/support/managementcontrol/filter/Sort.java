package it.codeland.support.managementcontrol.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Sort {
    Direction direction;
    List<String> properties;

    public String[] getProperties() {
        return properties.toArray(new String[0]);
    }
}
