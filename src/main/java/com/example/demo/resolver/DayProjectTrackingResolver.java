package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.DayProjectTracking;
import com.example.demo.model.DayProjectTrackingId;
import com.example.demo.repository.DayProjectTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

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
    public DayProjectTracking dayProjectTracking(@Argument DayProjectTrackingId idDayProjectTracking) {
        return dayProjectTrackingRepository.findById(idDayProjectTracking).orElse(null);
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
    public DeleteResponse deleteDayProjectTracking(@Argument DayProjectTrackingId idDayProjectTracking) {
        if (dayProjectTrackingRepository.existsById(idDayProjectTracking)) {
            dayProjectTrackingRepository.deleteById(idDayProjectTracking);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Day project tracking not found");
    }
}
