package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.DayProjectTracking;
import it.codeland.support.managementcontrol.model.DayProjectTrackingId;
import it.codeland.support.managementcontrol.repository.DayProjectTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DayProjectTrackingResolver {
    private final DayProjectTrackingRepository dayProjectTrackingRepository;

    @Autowired
    public DayProjectTrackingResolver(DayProjectTrackingRepository dayProjectTrackingRepository) {
        this.dayProjectTrackingRepository = dayProjectTrackingRepository;
    }

    @QueryMapping
    public Iterable<DayProjectTracking> allDayProjectTracking() {
        return dayProjectTrackingRepository.findAll();
    }

    @QueryMapping
    public DayProjectTracking dayProjectTracking(@Argument DayProjectTrackingId id) {
        return dayProjectTrackingRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public DayProjectTracking createDayProjectTracking(@Argument DayProjectTracking dayProjectTracking) {
        return dayProjectTrackingRepository.save(dayProjectTracking);
    }

    @MutationMapping
    public DayProjectTracking updateDayProjectTracking(@Argument DayProjectTracking dayProjectTracking) {
        return dayProjectTrackingRepository.save(dayProjectTracking);
    }

    @MutationMapping
    public DeleteResponse deleteDayProjectTracking(@Argument DayProjectTrackingId id) {
        if (dayProjectTrackingRepository.existsById(id)) {
            dayProjectTrackingRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Day project tracking not found");
    }
}
