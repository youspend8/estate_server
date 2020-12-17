package kr.co.estate.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.estate.common.TradeType;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.entity.QCityCodeEntity;
import kr.co.estate.entity.QTradeMasterEntity;
import kr.co.estate.entity.TradeMasterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TradeMasterRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QTradeMasterEntity qTradeMasterEntity = QTradeMasterEntity.tradeMasterEntity;
    private final QCityCodeEntity qCityCodeEntity = QCityCodeEntity.cityCodeEntity;

    public List<TradeMasterEntity> findBySearchQuery(SearchDto searchDto, boolean isPaging) {
        JPAQuery<TradeMasterEntity> jpaQuery = jpaQueryFactory.select(qTradeMasterEntity)
                .from(qTradeMasterEntity);

        BooleanExpression where;

        if ("TRADE".equals(searchDto.getTradeType())) {
            where = qTradeMasterEntity.tradeType.in(TradeType.APARTMENT_TRADE, TradeType.OFFICE_TRADE);
        } else if ("RENT".equals(searchDto.getTradeType())) {
            where = qTradeMasterEntity.tradeType.in(TradeType.APARTMENT_RENT, TradeType.OFFICE_RENT);
        } else {
            where = qTradeMasterEntity.tradeType.in(
                    TradeType.APARTMENT_TRADE, TradeType.APARTMENT_RENT, TradeType.OFFICE_TRADE, TradeType.OFFICE_RENT);
        }

        jpaQuery.where(where
                        .and(qTradeMasterEntity.location.regionCode.eq(searchDto.getRegion()))
                        .and(qTradeMasterEntity.location.sigunguCode.eq(searchDto.getSigungu())));

        ComparableExpressionBase<?> sortBy = qTradeMasterEntity.amount;

        switch (searchDto.getSortType()) {
            case "amount": {
                sortBy = qTradeMasterEntity.amount;
            } break;
            case "dealDate": {
                sortBy = qTradeMasterEntity.deal.dealDate;
            } break;
        }

        if ("desc".equals(searchDto.getSortMode())) {
            jpaQuery.orderBy(sortBy.desc());
        } else {
            jpaQuery.orderBy(sortBy.asc());
        }

        if (isPaging) {
            jpaQuery.offset(searchDto.getPage() * searchDto.getSize())
                    .limit(searchDto.getSize());
        }
        return jpaQuery.fetch();
    }
}
