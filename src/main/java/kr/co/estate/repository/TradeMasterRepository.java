package kr.co.estate.repository;

import kr.co.estate.entity.TradeMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMasterRepository extends JpaRepository<TradeMasterDTO, String>, JpaSpecificationExecutor<TradeMasterDTO> {
    Page<TradeMasterDTO> findAllByOrderByDealDateDesc(Pageable pageable);
}
