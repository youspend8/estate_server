package kr.co.estate.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.estate.entity.QCityCodeEntity;
import kr.co.estate.entity.QTradeMasterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TradeMasterRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QTradeMasterEntity qTradeMasterEntity = QTradeMasterEntity.tradeMasterEntity;
    private final QCityCodeEntity qCityCodeEntity = QCityCodeEntity.cityCodeEntity;

}
