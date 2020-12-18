package kr.co.estate.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.estate.common.TradeType;
import kr.co.estate.dto.query.TradeQuery;
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

    public List<TradeMasterEntity> findBySearchQuery(TradeQuery tradeQuery) {
        JPAQuery<TradeMasterEntity> jpaQuery = jpaQueryFactory.select(qTradeMasterEntity)
                .from(qTradeMasterEntity);

        BooleanExpression where;

        if ("TRADE".equals(tradeQuery.getTradeType())) {
            where = qTradeMasterEntity.tradeType.in(TradeType.APARTMENT_TRADE, TradeType.OFFICE_TRADE);
        } else if ("RENT".equals(tradeQuery.getTradeType())) {
            where = qTradeMasterEntity.tradeType.in(TradeType.APARTMENT_RENT, TradeType.OFFICE_RENT);
        } else {
            where = qTradeMasterEntity.tradeType.in(
                    TradeType.APARTMENT_TRADE, TradeType.APARTMENT_RENT, TradeType.OFFICE_TRADE, TradeType.OFFICE_RENT);
        }

        if (tradeQuery.getFromDate() != null) {
            where = where.and(qTradeMasterEntity.deal.dealDate.after(tradeQuery.getFromDate()));
        }
        if (tradeQuery.getToDate() != null) {
            where = where.and(qTradeMasterEntity.deal.dealDate.before(tradeQuery.getToDate()));
        }

        jpaQuery.where(where
                        .and(qTradeMasterEntity.location.regionCode.eq(tradeQuery.getRegion()))
                        .and(qTradeMasterEntity.location.sigunguCode.eq(tradeQuery.getSigungu())));

        ComparableExpressionBase<?> sortBy = qTradeMasterEntity.amount;

        if (tradeQuery.getSortType() != null) {
            switch (tradeQuery.getSortType()) {
                case "amount": {
                    sortBy = qTradeMasterEntity.amount;
                }
                break;
                case "dealDate": {
                    sortBy = qTradeMasterEntity.deal.dealDate;
                }
                break;
            }
            if ("desc".equals(tradeQuery.getSortMode())) {
                jpaQuery.orderBy(sortBy.desc());
            } else {
                jpaQuery.orderBy(sortBy.asc());
            }
        }

        if (tradeQuery.isPaging()) {
            jpaQuery.offset(tradeQuery.getPage() * tradeQuery.getSize())
                    .limit(tradeQuery.getSize());
        }
        return jpaQuery.fetch();
    }
}
