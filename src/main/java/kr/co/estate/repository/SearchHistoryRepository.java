package kr.co.estate.repository;

import kr.co.estate.entity.SearchHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryDTO, Long> {
}
