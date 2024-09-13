package com.example.demo.resolver;

import com.example.demo.bean.DeleteResponse;
import com.example.demo.model.Project;
import com.example.demo.model.RevenueStream;
import com.example.demo.repository.RevenueStreamRepository;
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
    public RevenueStream revenueStream(@Argument Long idRevenueStream) {
        return revenueStreamRepository.findById(idRevenueStream).orElse(null);
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
    public DeleteResponse deleteRevenueStream(@Argument Long idRevenueStream) {
        if (revenueStreamRepository.existsById(idRevenueStream)) {
            revenueStreamRepository.deleteById(idRevenueStream);
            return new DeleteResponse(true);
        }
        return new DeleteResponse(false, "Project not found");
    }
}
