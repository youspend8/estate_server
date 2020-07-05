package kr.co.estate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.entity.TradeMasterDTO;
import kr.co.estate.repository.TradeMasterRepository;
import kr.co.estate.repository.specification.TradeMasterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeMasterService {
    private final TradeMasterRepository tradeMasterRepository;

    public String fetchAll(SearchVO searchVO) throws JsonProcessingException {
        List<TradeMasterDTO> list = tradeMasterRepository
                .findAll(TradeMasterSpecification.searchBy(searchVO),
                        PageRequest.of(searchVO.getPage() - 1, searchVO.getSize(),
                                Sort.by("area").descending()))
                .toList();

        return new ObjectMapper().writeValueAsString(list);
    }

    /**
     * 평당가 제공
     * @param searchVO
     * @return
     */
    public String priceByPyung(SearchVO searchVO) throws JsonProcessingException {
        List<TradeMasterDTO> list = tradeMasterRepository
                .findAll(TradeMasterSpecification.searchBy(searchVO));

        List<String> dongList = list.stream()
                .map(TradeMasterDTO::getDong)
                .distinct()
                .collect(Collectors.toList());

        List<HashMap<String, Object>> returnList = dongList.stream()
                .map(x -> this.convertMap(list, x))
                .sorted(Comparator.comparingDouble((HashMap<String, Object> x) ->
                        (double) x.get("price")).reversed())
                .collect(Collectors.toList());

        return new ObjectMapper().writeValueAsString(returnList);
    }

    private HashMap<String, Object> convertMap(List<TradeMasterDTO> list, String dong) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("dong", dong);
        map.put("price", this.getPriceAverage(list, dong));
        map.put("count", this.getTradeCount(list, dong));
        return map;
    }

    private long getTradeCount(List<TradeMasterDTO> list, String dong) {
        return list.stream()
                .filter(x -> x.getDong().equals(dong))
                .distinct()
                .count();
    }
    private double getPriceAverage(List<TradeMasterDTO> list, String dong) {
        return list.stream().filter(x -> x.getDong().equals(dong))
                .distinct()
                .mapToLong(TradeMasterDTO::amountByPyung)
                .average()
                .orElse(-1);
    }
}
