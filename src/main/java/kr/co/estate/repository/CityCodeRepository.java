package kr.co.estate.repository;

import kr.co.estate.entity.CityCodeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCodeRepository extends JpaRepository<CityCodeDTO, String>, JpaSpecificationExecutor<CityCodeDTO> {
}
