package kr.co.estate.repository;

import kr.co.estate.entity.TradeMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeMasterRepository extends JpaRepository<TradeMasterDTO, String> {
    Page<TradeMasterDTO> findAllByOrderByDealDateDesc(Pageable pageable);
}
