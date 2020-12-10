package kr.co.estate.repository;

import kr.co.estate.entity.CityCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityCodeRepository extends JpaRepository<CityCodeEntity, String>, JpaSpecificationExecutor<CityCodeEntity> {
    List<CityCodeEntity> findByType(int type);
}
