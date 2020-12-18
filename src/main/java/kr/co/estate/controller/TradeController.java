package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.request.TradeSearchRequestDto;
import kr.co.estate.dto.request.TradeStatsPeriodRequestDto;
import kr.co.estate.dto.request.TradeStatsRequestDto;
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

    /**
     * API : 특정 지역 및 검색 쿼리에 따른 해당 지역의 실거래 내역 목록 값 요청
     * @param tradeStatsRequestDto {@link TradeSearchRequestDto}
     * @return {@link List<TradeSearchDto>}
     */
    @GetMapping(value = "/trade/search")
    @ApiOperation(value = SwaggerApiInfo.TRADE_SEARCH_VALUE, notes = SwaggerApiInfo.TRADE_SEARCH_NOTES)
    public ResponseEntity<ApiResponse<List<TradeSearchDto>>> search(
            @ModelAttribute TradeSearchRequestDto tradeSearchRequestDto) {
        log.debug("GET /trade/search :: request ==> tradeStatsRequestDto : {}", tradeSearchRequestDto);

        List<TradeSearchDto> result = tradeService.search(tradeSearchRequestDto);

        log.debug("GET /trade/search :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    /**
     * API : 특정 지역 및 검색 쿼리에 따른 해당 지역의 실거래 내역을 바탕으로 한 통계 값 요청
     * @param tradeStatsRequestDto {@link TradeStatsRequestDto}
     * @return {@link TradeStatsDto}
     */
    @GetMapping(value = "/trade/stats")
    @ApiOperation(value = SwaggerApiInfo.TRADE_STATS_VALUE, notes = SwaggerApiInfo.TRADE_STATS_NOTES)
    public ResponseEntity<ApiResponse<TradeStatsDto>> stats(
            @ModelAttribute TradeStatsRequestDto tradeStatsRequestDto) {
        log.debug("GET /trade/stats :: request ==> tradeStatsRequestDto : {}", tradeStatsRequestDto);

        TradeStatsDto result = tradeService.stats(tradeStatsRequestDto);

        log.debug("GET /trade/stats :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    /**
     * API : 특정 기간 및 검색 쿼리에 따른 해당 지역의 실거래 내역을 바탕으로 한 통계 값 요청
     * @param tradeStatsPeriodRequestDto {@link TradeStatsPeriodRequestDto}
     * @return {@link TradeStatsPeriodDto}
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

    /**
     * API : 특정 좌표(위도, 경도)를 기준으로 Zoom Level에 따라 허용 거리에 포함되는 지역 목록을 기준으로 실거래 내역의 집계(지역별 평균 가격, 지역별 총 거래건수 등) 값 요청
     * @param naverMapDto {@link NaverMapDto}
     * @param naverMapFilterDto {@link NaverMapFilterDto}
     * @return {@link List<TradeAggsDto>}
     */
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
