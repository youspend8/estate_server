package kr.co.estate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.config.properties.JsonFilesProperties;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.dto.trade.NaverMapFilterDto;
import kr.co.estate.dto.trade.TradeAggsDto;
import kr.co.estate.dto.trade.TradeSearchDto;
import kr.co.estate.dto.trade.TradeStatsDto;
import kr.co.estate.dto.trade.embedded.stats.TradeStatsCityDto;
import kr.co.estate.entity.TradeMasterEntity;
import kr.co.estate.mapper.TradeMasterMapper;
import kr.co.estate.repository.TradeMasterRepository;
import kr.co.estate.repository.querydsl.TradeMasterRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeMasterService {
    private final TradeMasterRepository tradeMasterRepository;
    private final TradeMasterMapper tradeMasterMapper;
    private final ObjectMapper objectMapper;
    private final JsonFilesProperties jsonFilesProperties;
    private final TradeMasterRepositorySupport tradeMasterRepositorySupport;

    public List<TradeAggsDto> aggregateJson(NaverMapDto naverMapDto, NaverMapFilterDto naverMapFilterDto) {
        List<TradeAggsDto> list = new ArrayList<>();
        try {
            list = objectMapper.readValue(new File(
                    jsonFilesProperties.getAggregationPath(
                            naverMapDto.typeByZoom(), naverMapFilterDto.getTradeType())), new TypeReference<List<TradeAggsDto>>() {});
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

    @Transactional(readOnly = true)
    public List<TradeSearchDto> search(SearchDto searchDto) {
        return tradeMasterRepositorySupport.findBySearchQuery(searchDto, true)
                .stream()
                .map(TradeSearchDto::valueOf)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TradeStatsDto stats(SearchDto searchDto) {
        List<TradeMasterEntity> list =
                tradeMasterRepositorySupport.findBySearchQuery(searchDto, false);

        List<String> dongList = list.stream()
                .map(entity -> entity.getLocation().getDong())
                .distinct()
                .collect(Collectors.toList());

        List<TradeStatsCityDto> returnList = dongList.stream()
                .map(x -> TradeStatsCityDto.of(x, getPriceAverage(list, x), getTradeCount(list, x)))
                .sorted(Comparator.comparingDouble(TradeStatsCityDto::getPrice).reversed())
                .collect(Collectors.toList());

        return TradeStatsDto.builder()
                .cityList(returnList)
                .priceAverage(returnList.stream()
                        .mapToDouble(TradeStatsCityDto::getPrice)
                        .average()
                        .orElse(0))
                .countSum(returnList.stream()
                        .mapToLong(TradeStatsCityDto::getCount)
                        .sum())
                .build();
    }

    private long getTradeCount(List<TradeMasterEntity> list, String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .count();
    }
    private double getPriceAverage(List<TradeMasterEntity> list, String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .mapToLong(TradeMasterEntity::amountByPyung)
                .average()
                .orElse(-1);
    }
}
