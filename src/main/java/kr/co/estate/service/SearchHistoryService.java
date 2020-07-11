package kr.co.estate.service;

import kr.co.estate.entity.SearchHistoryDTO;
import kr.co.estate.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public void save(String ga, Map<String, Object> params) {
        SearchHistoryDTO searchHistoryDTO = SearchHistoryDTO.builder()
                .ga(ga)
                .keyword(params.get("keyword").toString())
                .build();

        searchHistoryRepository.save(searchHistoryDTO);
    }
}
