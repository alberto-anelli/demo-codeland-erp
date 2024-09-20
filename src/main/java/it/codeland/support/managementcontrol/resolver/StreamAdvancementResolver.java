package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.StreamAdvancement;
import it.codeland.support.managementcontrol.model.StreamAdvancementId;
import it.codeland.support.managementcontrol.model.StreamAdvancementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StreamAdvancementResolver {
    private final StreamAdvancementRepository streamAdvancementRepository;

    @Autowired
    public StreamAdvancementResolver(StreamAdvancementRepository streamAdvancementRepository) {
        this.streamAdvancementRepository = streamAdvancementRepository;
    }

    @QueryMapping
    public Iterable<StreamAdvancement> allStreamAdvancement() {
        return streamAdvancementRepository.findAll();
    }

    @QueryMapping
    public StreamAdvancement streamAdvancement(@Argument StreamAdvancementId id) {
        return streamAdvancementRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public StreamAdvancement createStreamAdvancement(@Argument StreamAdvancement streamAdvancement) {
        return streamAdvancementRepository.save(streamAdvancement);
    }

    @MutationMapping
    public StreamAdvancement updateStreamAdvancement(@Argument StreamAdvancement streamAdvancement) {
        return streamAdvancementRepository.save(streamAdvancement);
    }

    @MutationMapping
    public DeleteResponse deleteStreamAdvancement(@Argument StreamAdvancementId id) {
        if (streamAdvancementRepository.findById(id).isPresent()) {
            streamAdvancementRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Project not found");
    }
}
