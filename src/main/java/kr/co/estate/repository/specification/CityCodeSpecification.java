package kr.co.estate.repository.specification;

import kr.co.estate.entity.CityCodeDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CityCodeSpecification {

    public static Specification<CityCodeDTO> searchBy(Map<String, Object> params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();

            int type = Integer.parseInt((String) params.get("type"));

            predicate.add(criteriaBuilder.equal(root.get("type"), type));

            if (type == 1) {
                predicate.add(criteriaBuilder.equal(root.get("region"), params.get("region")));
            } else if (type == 2) {
                predicate.add(criteriaBuilder.equal(root.get("sigungu"), params.get("sigungu")));
            }
            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        };
    }
}
