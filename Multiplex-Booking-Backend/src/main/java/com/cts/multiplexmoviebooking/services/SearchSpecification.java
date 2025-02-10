package com.cts.multiplexmoviebooking.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.cts.multiplexmoviebooking.dtos.SearchDTO;
import com.cts.multiplexmoviebooking.models.Shows;

@Component
public class SearchSpecification {

	public Specification<Shows> getfilteredShows(SearchDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (dto.getDate() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("fromDate"), dto.getDate()),
                		criteriaBuilder.greaterThanOrEqualTo(root.get("toDate"), dto.getDate())));
            }
            if (dto.getDate() == null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("fromDate"), dto.getDate()),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("toDate"), LocalDate.now())));
            }
            if (dto.getMovieName() != null && !dto.getMovieName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("movie").get("movieName")),
                        "%" + dto.getMovieName().toLowerCase() + "%"));
            }

            if (dto.getSlot()>0) {
                predicates.add(criteriaBuilder.equal(root.get("slot"), dto.getSlot()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("showId")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
