package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DayProjectTrackingId {
    private Long idProject;
    private Long idCollaborator;
    private LocalDate day;
}
