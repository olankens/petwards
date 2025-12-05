package org.example.petwards.il.filters.specifications;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.example.petwards.dl.entities.Beast;
import org.example.petwards.dl.entities.Capability;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Set;

public class BeastSpecification {
    public static Specification<Beast> hasNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                (name == null) ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Beast> hasCapabilities(List<String> capabilities) {
        return (root, query, criteriaBuilder) -> {
            if (capabilities == null || capabilities.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            Join<Beast, Capability> capabilityJoin = root.join("capabilities");
            return capabilityJoin.get("name").in(capabilities);
        };
    }

    public static Specification<Beast> joinCapabilities() {
        return (root, query, criteriaBuilder) -> {
            if (query.getResultType() != Long.class) {
                root.fetch("capabilities", JoinType.LEFT);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
