package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.dto.trade.NaverMapFilterDto;
import kr.co.estate.dto.trade.TradeAggsDto;
import kr.co.estate.dto.trade.TradeSearchDto;
import kr.co.estate.dto.trade.TradeStatsDto;
import kr.co.estate.service.TradeServiceImpl;
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
    private final TradeServiceImpl tradeServiceImpl;

    @GetMapping(value = "/trade/search")
    @ApiOperation(value = SwaggerApiInfo.TRADE_SEARCH_VALUE, notes = SwaggerApiInfo.TRADE_SEARCH_NOTES)
    public ResponseEntity<ApiResponse<List<TradeSearchDto>>> search(
            @ModelAttribute SearchDto searchDto) {
        log.debug("GET /trade/search :: request ==> searchDto : {}", searchDto);

        List<TradeSearchDto> result = tradeServiceImpl.search(searchDto);

        log.debug("GET /trade/search :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    @GetMapping(value = "/trade/stats")
    @ApiOperation(value = SwaggerApiInfo.TRADE_STATS_VALUE, notes = SwaggerApiInfo.TRADE_STATS_NOTES)
    public ResponseEntity<ApiResponse<TradeStatsDto>> stats(
            @ModelAttribute SearchDto searchDto) {
        log.debug("GET /trade/stats :: request ==> searchDto : {}", searchDto);

        TradeStatsDto result = tradeServiceImpl.stats(searchDto);

        log.debug("GET /trade/stats :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }

    @GetMapping(value = "/trade/aggregate")
    @ApiOperation(value = SwaggerApiInfo.TRADE_AGGREGATE_VALUE, notes = SwaggerApiInfo.TRADE_AGGREGATE_NOTES)
    public ResponseEntity<ApiResponse<List<TradeAggsDto>>> aggregate(
            @ModelAttribute NaverMapDto naverMapDto,
            @ModelAttribute NaverMapFilterDto naverMapFilterDto) {
        log.debug("GET /trade/aggregate :: request ==> coordinatesDto : {}", naverMapDto);

        List<TradeAggsDto> result = tradeServiceImpl.aggregateJson(naverMapDto, naverMapFilterDto);

        log.debug("GET /trade/aggregate :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }
}
