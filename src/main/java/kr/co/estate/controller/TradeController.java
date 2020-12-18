package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.query.TradeQuery;
import kr.co.estate.dto.request.TradeStatsPeriodRequestDto;
import kr.co.estate.dto.trade.*;
import kr.co.estate.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "실거래 데이터")
public class TradeController {
    private final TradeService tradeService;

    @GetMapping(value = "/trade/search")
    @ApiOperation(value = SwaggerApiInfo.TRADE_SEARCH_VALUE, notes = SwaggerApiInfo.TRADE_SEARCH_NOTES)
    public ResponseEntity<ApiResponse<List<TradeSearchDto>>> search(
            @ModelAttribute TradeQuery tradeQuery) {
        log.debug("GET /trade/search :: request ==> searchDto : {}", tradeQuery);

        List<TradeSearchDto> result = tradeService.search(tradeQuery);

        log.debug("GET /trade/search :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    @GetMapping(value = "/trade/stats")
    @ApiOperation(value = SwaggerApiInfo.TRADE_STATS_VALUE, notes = SwaggerApiInfo.TRADE_STATS_NOTES)
    public ResponseEntity<ApiResponse<TradeStatsDto>> stats(
            @ModelAttribute TradeQuery tradeQuery) {
        log.debug("GET /trade/stats :: request ==> searchDto : {}", tradeQuery);

        TradeStatsDto result = tradeService.stats(tradeQuery);

        log.debug("GET /trade/stats :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    /**
     * API : 특정 기간 및 검색 쿼리에 따른 해당 지역의 실거래 내역을 바탕으로 한 통계 값 요청
     * @param tradeStatsPeriodRequestDto {@link TradeStatsPeriodRequestDto}
     * @return {@link TradeStatsDto}
     */
    @GetMapping(value = "/trade/stats/period")
    @ApiOperation(value = SwaggerApiInfo.TRADE_STATS_PERIOD_VALUE, notes = SwaggerApiInfo.TRADE_STATS_PERIOD_NOTES)
    public ResponseEntity<ApiResponse<TradeStatsPeriodDto>> statsPeriod(
            @ModelAttribute TradeStatsPeriodRequestDto tradeStatsPeriodRequestDto) {
        log.debug("GET /trade/stats/period :: request ==> tradeStatsPeriodRequestDto : {}", tradeStatsPeriodRequestDto);

        TradeStatsPeriodDto result = tradeService.statsPeriod(tradeStatsPeriodRequestDto);

        log.debug("GET /trade/stats/period :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    @GetMapping(value = "/trade/aggregate")
    @ApiOperation(value = SwaggerApiInfo.TRADE_AGGREGATE_VALUE, notes = SwaggerApiInfo.TRADE_AGGREGATE_NOTES)
    public ResponseEntity<ApiResponse<List<TradeAggsDto>>> aggregate(
            @ModelAttribute NaverMapDto naverMapDto,
            @ModelAttribute NaverMapFilterDto naverMapFilterDto) {
        log.debug("GET /trade/aggregate :: request ==> naverMapDto : {}", naverMapDto);

        List<TradeAggsDto> result = tradeService.aggregateJson(naverMapDto, naverMapFilterDto);

        log.debug("GET /trade/aggregate :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }
}
