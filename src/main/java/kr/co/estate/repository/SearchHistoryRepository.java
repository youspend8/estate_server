package kr.co.estate.repository;

import kr.co.estate.entity.SearchHistoryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryDTO, Long> {
    @Query(value="SELECT SH FROM SearchHistoryDTO SH WHERE SH.ga = :ga " +
            "GROUP BY SH.keyword")
    public List<SearchHistoryDTO> findAllByQuery(
            @Param("ga") String ga, Pageable pageable);
}
