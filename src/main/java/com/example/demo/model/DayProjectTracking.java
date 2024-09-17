package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = DayProjectTracking.TABLE_NAME)
//@IdClass(DayProjectTrackingId.class)
public class DayProjectTracking extends ErpAuditableEntity<DayProjectTrackingId> {

    public static final String TABLE_NAME = "day_project_tracking";

    @EmbeddedId
    private DayProjectTrackingId id;

    @ManyToOne
    @JoinColumn(name = "id_project", nullable = false, insertable = false, updatable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_collaborator", nullable = false, insertable = false, updatable = false)
    private Collaborator collaborator;

    @Column(name = "hours")
    private int hours;

    @Column(name = "extra_no_night")
    private int extraNoNight;

    @Column(name = "extra_night")
    private int extraNight;

    @Column(name = "availability")
    private int availability;

    @Column(name = "moreInfo", length = 100)
    private String moreInfo;

    @Column(name = "notes")
    private String notes;
}
