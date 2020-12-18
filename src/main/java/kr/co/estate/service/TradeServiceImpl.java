package kr.co.estate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.config.properties.JsonFilesProperties;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.request.TradeSearchRequestDto;
import kr.co.estate.dto.request.TradeStatsPeriodRequestDto;
import kr.co.estate.dto.request.TradeStatsRequestDto;
import kr.co.estate.dto.trade.*;
import kr.co.estate.dto.trade.embedded.stats.TradeStatsCityDto;
import kr.co.estate.dto.trade.embedded.stats.TradeStatsPeriodItem;
import kr.co.estate.entity.TradeMasterEntity;
import kr.co.estate.entity.collection.TradeMasterEntities;
import kr.co.estate.mapper.TradeMasterMapper;
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
public class TradeServiceImpl implements TradeService {
    private final TradeMasterMapper tradeMasterMapper;
    private final ObjectMapper objectMapper;
    private final JsonFilesProperties jsonFilesProperties;
    private final TradeMasterRepositorySupport tradeMasterRepositorySupport;

    @Override
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
                .filter(x -> x.isContainArea(
                        naverMapDto.getNorthLat(),
                        naverMapDto.getSouthLat(),
                        naverMapDto.getWestLong(),
                        naverMapDto.getEastLong()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TradeAggsDto> aggregate(NaverMapDto naverMapDto, NaverMapFilterDto naverMapFilterDto) {
        return tradeMasterMapper
                .aggregateByCity(naverMapDto.typeByZoom(), naverMapDto)
                .stream()
                .map(TradeAggsDto::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TradeSearchDto> search(TradeSearchRequestDto tradeSearchRequestDto) {
        return tradeMasterRepositorySupport
                .findBySearchQuery(tradeSearchRequestDto.asQuery())
                .stream()
                .map(TradeSearchDto::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TradeStatsDto stats(TradeStatsRequestDto tradeStatsRequestDto) {
        List<TradeMasterEntity> list = tradeMasterRepositorySupport
                .findBySearchQuery(tradeStatsRequestDto.asQuery());

        TradeMasterEntities tradeMasterEntities = new TradeMasterEntities(list);

        List<TradeStatsCityDto> result = tradeMasterEntities
                .getDistinctDong()
                .stream()
                .map(x -> TradeStatsCityDto.of(x,
                        tradeMasterEntities.filterDongDistinctAmountAverage(x),
                        tradeMasterEntities.filterDongDistinctCount(x)))
                .sorted(Comparator.comparingDouble(TradeStatsCityDto::getPrice).reversed())
                .collect(Collectors.toList());

        return TradeStatsDto.valueOf(result);
    }

    @Override
    @Transactional(readOnly = true)
    public TradeStatsPeriodDto statsPeriod(TradeStatsPeriodRequestDto tradeStatsPeriodRequestDto) {
        List<TradeMasterEntity> list = tradeMasterRepositorySupport
                .findBySearchQuery(tradeStatsPeriodRequestDto.asQuery());

        TradeMasterEntities tradeMasterEntities = new TradeMasterEntities(list);

        List<TradeStatsPeriodItem> result = tradeMasterEntities
                .getDistinctDealDate()
                .stream()
                .map(x -> TradeStatsPeriodItem.of(x,
                        tradeMasterEntities.filterDealDateDistinctAmountAverage(x),
                        tradeMasterEntities.filterDealDateDistinctCount(x)))
                .sorted(Comparator.comparing(TradeStatsPeriodItem::getDate))
                .collect(Collectors.toList());

        return TradeStatsPeriodDto.valueOf(result);
    }
}
