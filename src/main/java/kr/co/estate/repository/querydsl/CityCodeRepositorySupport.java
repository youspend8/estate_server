package kr.co.estate.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.entity.CityCodeEntity;
import kr.co.estate.entity.QCityCodeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CityCodeRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final QCityCodeEntity qCityCodeEntity = QCityCodeEntity.cityCodeEntity;

    public List<CityCodeEntity> findByTypeAndNaverMap(int type, NaverMapDto naverMapDto) {
        return jpaQueryFactory.select(qCityCodeEntity)
                .from(qCityCodeEntity)
                .where(qCityCodeEntity.type.eq(type)
                        .and(qCityCodeEntity.coordinate.longitude.between(
                                naverMapDto.getWestLong(), naverMapDto.getEastLong()))
                        .and(qCityCodeEntity.coordinate.latitude.between(
                                naverMapDto.getSouthLat(), naverMapDto.getNorthLat())))
                .fetch();
    }
}
