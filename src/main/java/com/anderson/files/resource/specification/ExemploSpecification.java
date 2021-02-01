package com.anderson.files.resource.specification;

import com.anderson.files.domain.data.entity.Exemplo;
import com.anderson.files.domain.data.entity.Exemplo_;
import com.anderson.files.domain.data.wrapper.filter.ExemploFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ExemploSpecification implements Specification<Exemplo> {
    private static final long serialVersionUID=1l;
    
    private final ExemploFilter filter;
    
    public static ExemploSpecification of(ExemploFilter filter){
        if(filter == null) return null;
        return new ExemploSpecification(filter);
    }
    
    @Override
    public Predicate toPredicate(Root<Exemplo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        
        if(filter.getId() != null && !filter.getId().isEmpty()){
            predicates.add(root.get(Exemplo_.id).in(filter.getId()));
        }

        if(filter.getName() != null && !filter.getName().isEmpty()){
            predicates.add(root.get(Exemplo_.name).in(filter.getName()));
        }

        if (filter.getCreatedAtAfter() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get(Exemplo_.createdAt), filter.getCreatedAtAfter().atStartOfDay()));
        }

        if (filter.getCreatedAtBefore() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get(Exemplo_.createdAt), filter.getCreatedAtBefore().atStartOfDay()));
        }

        if (filter.getUpdatedAtAfter() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get(Exemplo_.updatedAt), filter.getUpdatedAtAfter().atStartOfDay()));
        }

        if (filter.getUpdatedAtBefore() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get(Exemplo_.updatedAt), filter.getUpdatedAtBefore().atStartOfDay()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
