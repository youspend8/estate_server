package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.dto.trade.TradeAggsDto;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "실거래 데이터")
public class TradeController {
    private final TradeMasterService tradeMasterService;

    @GetMapping(value = "/trade/search")
    public ResponseEntity<Map<String, Object>> search(
            @ModelAttribute SearchDto searchDto) throws JsonProcessingException {
        log.info("searchDto ==> {}", searchDto);

        return ResponseEntity
                .ok(tradeMasterService.fetchAll(searchDto));
    }

    @GetMapping(value = "/trade/stats")
    public ResponseEntity<Map<String, Object>> stats(
            @ModelAttribute SearchDto searchDto) throws JsonProcessingException {
        log.info("searchDto ==> {}", searchDto);

        return ResponseEntity
                .ok(tradeMasterService.priceByPyung(searchDto));
    }

    @GetMapping(value = "/trade/aggregate")
    @ApiOperation(value = SwaggerApiInfo.TRADE_AGGREGATE_VALUE, notes = SwaggerApiInfo.TRADE_AGGREGATE_NOTES)
    public ResponseEntity<ApiResponse<List<TradeAggsDto>>> aggregate(
            @ModelAttribute NaverMapDto naverMapDto) {
        log.debug("coordinatesDto ==> {}", naverMapDto);

        return ResponseEntity.ok(
                ApiResponse.valueOf(tradeMasterService.aggregate(naverMapDto)));
    }
}
