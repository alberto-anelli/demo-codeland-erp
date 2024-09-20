package it.codeland.support.managementcontrol.repository;

import it.codeland.support.managementcontrol.model.DayProjectTracking;
import it.codeland.support.managementcontrol.model.DayProjectTrackingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayProjectTrackingRepository extends JpaRepository<DayProjectTracking, DayProjectTrackingId> {
}
