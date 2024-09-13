package com.example.demo.repository;

import com.example.demo.model.DayProjectTracking;
import com.example.demo.model.DayProjectTrackingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayProjectTrackingRepository extends JpaRepository<DayProjectTracking, DayProjectTrackingId> {
}
