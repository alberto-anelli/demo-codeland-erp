package it.codeland.support.managementcontrol.resolver;

import it.codeland.support.managementcontrol.bean.DeleteResponse;
import it.codeland.support.managementcontrol.model.RevenueStream;
import it.codeland.support.managementcontrol.repository.RevenueStreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class RevenueStreamResolver {
    private RevenueStreamRepository revenueStreamRepository;

    @Autowired
    public RevenueStreamResolver(RevenueStreamRepository revenueStreamRepository) {
        this.revenueStreamRepository = revenueStreamRepository;
    }

    @QueryMapping
    public Iterable<RevenueStream> allRevenueStream() {
        return revenueStreamRepository.findAll();
    }

    @QueryMapping
    public RevenueStream revenueStream(@Argument Long id) {
        return revenueStreamRepository.findById(id).orElse(null);
    }

    @MutationMapping
    public RevenueStream createRevenueStream(@Argument RevenueStream revenueStream) {
        return revenueStreamRepository.save(revenueStream);
    }

    @MutationMapping
    public RevenueStream updateRevenueStream(@Argument RevenueStream revenueStream) {
        return revenueStreamRepository.save(revenueStream);
    }

    @MutationMapping
    public DeleteResponse deleteRevenueStream(@Argument Long id) {
        Optional<RevenueStream> revenueStream = revenueStreamRepository.findById(id);
        if (revenueStream.isPresent()) {
            revenueStreamRepository.deleteById(id);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Project not found");
    }
}
