package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/trade")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class TradeController {
    private final TradeMasterService tradeMasterService;

    @GetMapping(value = "/search")
    public ResponseEntity<Map<String, Object>> search(
            @ModelAttribute SearchDto searchDto) throws JsonProcessingException {
        log.info("searchDto ==> {}", searchDto);

        return ResponseEntity
                .ok(tradeMasterService.fetchAll(searchDto));
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<Map<String, Object>> stats(
            @ModelAttribute SearchDto searchDto) throws JsonProcessingException {
        log.info("searchDto ==> {}", searchDto);

        return ResponseEntity
                .ok(tradeMasterService.priceByPyung(searchDto));
    }

    @GetMapping(value = "/aggregate")
    public ResponseEntity<?> aggregate(
            @ModelAttribute NaverMapDto naverMapDto) {
        log.debug("coordinatesDto ==> {}", naverMapDto);

        return ResponseEntity
                .ok(tradeMasterService.aggregate(naverMapDto));
    }
}
