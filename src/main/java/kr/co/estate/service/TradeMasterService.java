package kr.co.estate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.config.properties.JsonFilesProperties;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.dto.trade.TradeAggsDto;
import kr.co.estate.entity.TradeMasterEntity;
import kr.co.estate.mapper.TradeMasterMapper;
import kr.co.estate.repository.TradeMasterRepository;
import kr.co.estate.repository.specification.TradeMasterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeMasterService {
    private final TradeMasterRepository tradeMasterRepository;
    private final TradeMasterMapper tradeMasterMapper;
    private final ObjectMapper objectMapper;
    private final JsonFilesProperties jsonFilesProperties;

    public List<TradeAggsDto> aggregateJson(NaverMapDto naverMapDto) {
        List<TradeAggsDto> list = new ArrayList<>();
        try {
            list = objectMapper.readValue(new File(
                    jsonFilesProperties.getAggregationPath(naverMapDto.typeByZoom())), new TypeReference<List<TradeAggsDto>>() {});
        } catch (IOException e) {
            // TODO : throw custom exception
        }
        return list.stream()
                .filter(x -> x.isContainArea(naverMapDto.getNorthLat(), naverMapDto.getSouthLat(), naverMapDto.getWestLong(), naverMapDto.getEastLong()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TradeAggsDto> aggregate(NaverMapDto naverMapDto) {
        return tradeMasterMapper.aggregateByCity(naverMapDto.typeByZoom(), naverMapDto)
//        return tradeMasterMapper.aggregateByCity(3, naverMapDto)
                .stream()
                .map(TradeAggsDto::valueOf)
                .collect(Collectors.toList());
    }

    public Map<String, Object> fetchAll(SearchDto searchDto) throws JsonProcessingException {
        Page<TradeMasterEntity> page = tradeMasterRepository
                .findAll(TradeMasterSpecification.searchBy(searchDto),
                        PageRequest.of(searchDto.getPage() - 1, searchDto.getSize(), TradeMasterSpecification.sortBy(searchDto)));

        Map<String, Object> result = new HashMap<>();
        result.put("list", page.toList());
        result.put("totalPage", page.getTotalPages());
        return result;
    }

    /**
     * 평당가 제공
     * @param searchDto
     * @return
     */
    public Map<String, Object> priceByPyung(SearchDto searchDto) throws JsonProcessingException {
        List<TradeMasterEntity> list = tradeMasterRepository
                .findAll(TradeMasterSpecification.searchBy(searchDto));

        List<String> dongList = list.stream()
                .map(entity -> entity.getLocation().getDong())
                .distinct()
                .collect(Collectors.toList());

        List<HashMap<String, Object>> returnList = dongList.stream()
                .map(x -> this.convertMap(list, x))
                .sorted(Comparator.comparingDouble((HashMap<String, Object> x) ->
                        (double) x.get("price")).reversed())
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("list", returnList);
        result.put("priceAvg", returnList.stream().mapToDouble(x -> (double) x.get("price")).average());
        result.put("countSum", returnList.stream().mapToLong(x -> (long) x.get("count")).sum());

        return result;
    }

    private HashMap<String, Object> convertMap(List<TradeMasterEntity> list, String dong) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("dong", dong);
        map.put("price", this.getPriceAverage(list, dong));
        map.put("count", this.getTradeCount(list, dong));
        return map;
    }

    private long getTradeCount(List<TradeMasterEntity> list, String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .count();
    }
    private double getPriceAverage(List<TradeMasterEntity> list, String dong) {
        return list.stream().filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .mapToLong(TradeMasterEntity::amountByPyung)
                .average()
                .orElse(-1);
    }
}
