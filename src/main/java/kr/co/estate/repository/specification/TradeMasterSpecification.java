package kr.co.estate.repository.specification;

import kr.co.estate.common.TradeType;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.entity.TradeMasterDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TradeMasterSpecification {

    public static Specification<TradeMasterDTO> searchBy(SearchVO searchVO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (searchVO.getName() != null && !searchVO.getName().equals("")) {
                predicate.add(criteriaBuilder.equal(root.get("name"), searchVO.getName()));
            }
            String region = searchVO.getRegion();
            String sigungu = searchVO.getSigungu();

            predicate.add(criteriaBuilder.equal(root.get("regionCode"), region + sigungu));
            predicate.add(criteriaBuilder.equal(root.get("tradeType"), TradeType.APARTMENT_TRADE));
            return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
        };
    }
}
