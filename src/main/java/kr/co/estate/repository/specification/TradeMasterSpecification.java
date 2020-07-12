package kr.co.estate.repository.specification;

import kr.co.estate.common.TradeType;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.entity.TradeMasterDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TradeMasterSpecification {

    public static Specification<TradeMasterDTO> searchBy(SearchVO searchVO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (searchVO.getName() != null && !searchVO.getName().equals("")) {
                predicate.add(criteriaBuilder.like(root.get("name"), "%" + searchVO.getName() + "%"));
            }
            String region = searchVO.getRegion();
            String sigungu = searchVO.getSigungu();

            predicate.add(criteriaBuilder.equal(root.get("regionCode"), region + sigungu));
            if (searchVO.getStartDate() != null && !searchVO.getStartDate().equals("")) {
                String startDate = searchVO.getStartDate();
                String endDate = searchVO.getEndDate();
                predicate.add(criteriaBuilder.between(root.get("dealYear"),
                        Integer.parseInt(startDate.split("-")[0]), Integer.parseInt(endDate.split("-")[0])));
                predicate.add(criteriaBuilder.between(root.get("dealMonth"),
                        Integer.parseInt(startDate.split("-")[1]), Integer.parseInt(endDate.split("-")[1])));
            }
            if (searchVO.getTradeType() != null && !searchVO.getTradeType().equals("")) {
                if (searchVO.getTradeType().equals("trade")) {
                    predicate.add(criteriaBuilder.equal(root.get("tradeType"), TradeType.APARTMENT_TRADE));
                } else if (searchVO.getTradeType().equals("rent")) {
                    predicate.add(criteriaBuilder.equal(root.get("tradeType"), TradeType.APARTMENT_RENT));
                }
            }
            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        };
    }

    public static Sort sortBy(SearchVO searchVO) {
        String sortType = searchVO.getSortType();
        String sortMode = searchVO.getSortMode();

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
