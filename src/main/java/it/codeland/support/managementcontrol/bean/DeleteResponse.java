package it.codeland.support.managementcontrol.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteResponse {
    private Boolean ok;
    private String message;

    public DeleteResponse(Boolean ok) {
        this.ok = ok;
    }
}
