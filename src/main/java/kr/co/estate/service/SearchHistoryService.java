package kr.co.estate.service;

import kr.co.estate.entity.SearchHistoryDTO;
import kr.co.estate.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
                .createDate(new Date())
                .build();

        searchHistoryRepository.save(searchHistoryDTO);
    }

    @Transactional
    public Map<String, Object> fetch(String ga) {
        List<SearchHistoryDTO> list = searchHistoryRepository.findAllByQuery(ga,
                PageRequest.of(0, 100));

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalCount", list.size());
        return result;
    }
}
