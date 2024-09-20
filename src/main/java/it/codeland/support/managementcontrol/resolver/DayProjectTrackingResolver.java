package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.exception.EntityNotFoundException;
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
    private final DayProjectTrackingRepository repository;

    public DayProjectTrackingResolver(DayProjectTrackingRepository dayProjectTrackingRepository) {
        this.repository = dayProjectTrackingRepository;
    }

    @QueryMapping
    public Iterable<DayProjectTracking> allDayProjectTracking() {
        return repository.findAll();
    }

    @QueryMapping
    public DayProjectTracking dayProjectTracking(@Argument DayProjectTrackingId id) {
        return repository.findById(id).orElse(null);
    }

    @MutationMapping
    public DayProjectTracking updateDayProjectTracking(@Argument DayProjectTracking dayProjectTracking) {
        return repository.save(dayProjectTracking);
    }

    @MutationMapping
    public Boolean deleteDayProjectTracking(@Argument DayProjectTrackingId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Day project tracking (collaborator:{0}, project:{1}, day:{2}) not found",
                id.getIdCollaborator(), id.getIdProject(), id.getDay());
    }
}
