package kr.co.estate.repository.specification;

import kr.co.estate.common.TradeType;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.entity.TradeMasterEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TradeMasterSpecification {

    public static Specification<TradeMasterEntity> searchBy(SearchDto searchDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (searchDto.getName() != null && !searchDto.getName().equals("")) {
                predicate.add(criteriaBuilder.equal(root.get("name"), searchDto.getName()));
            }
            String region = searchDto.getRegion();
            String sigungu = searchDto.getSigungu();

            predicate.add(criteriaBuilder.equal(root.get("regionCode"), region + sigungu));
            predicate.add(criteriaBuilder.equal(root.get("tradeType"), TradeType.APARTMENT_TRADE));
            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        };
    }

    public static Sort sortBy(SearchDto searchDto) {
        String sortType = searchDto.getSortType();
        String sortMode = searchDto.getSortMode();

        Sort sort = Sort.by("amount").descending();

        if (sortType == null || sortMode == null) {
            return sort;
        }
        if (sortType.equals("dealDate")) {
            if (sortMode.equals("desc")) {
                sort = Sort.by("dealYear").descending().and(Sort.by("dealMonth").descending().and(Sort.by("dealDay").descending()));
            } else {
                sort = Sort.by("dealYear").ascending().and(Sort.by("dealMonth").ascending().and(Sort.by("dealDay").ascending()));
            }
        } else {
            if (sortType.equals("amount")) {
                sort = Sort.by("amount");
            } else if (sortType.equals("area")) {
                sort = Sort.by("area");
            }
            if (sortMode.equals("desc")) {
                sort = sort.descending();
            } else {
                sort = sort.ascending();
            }
        }

        return sort;
    }
}
