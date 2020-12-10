package kr.co.estate.repository;

import kr.co.estate.entity.TradeMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeMasterRepository extends JpaRepository<TradeMasterEntity, String>, JpaSpecificationExecutor<TradeMasterEntity> {
    @Query(value = "" +
            "SELECT  *, " +
            "(" +
            "   6371 * acos(cos(radians(:lat)) * cos(radians(ST_X(COORDINATE))) * cos(radians(ST_Y(COORDINATE))" +
            "   - radians(:lon)) + sin(radians(:lat)) * sin(radians(ST_X(COORDINATE))))" +
            ") AS DISTANCE " +
            "FROM       TRADE_MASTER " +
            "WHERE      TRUE " +
            "HAVING     DISTANCE <= 10 " +
            "ORDER BY   DISTANCE",
            nativeQuery = true
    )
    List<TradeMasterEntity> findAllByCoordinate(@Param("lat") double lat, @Param("lon") double lon);
}
