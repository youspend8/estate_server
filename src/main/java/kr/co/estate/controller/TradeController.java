package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.entity.SearchVO;
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
            @ModelAttribute SearchVO searchVO) throws JsonProcessingException {
        log.info(">> searchVo :: " + searchVO);

        return ResponseEntity
                .ok(tradeMasterService.fetchAll(searchVO));
    }

    @GetMapping(value = "/stats")
    public ResponseEntity<Map<String, Object>> stats(
            @ModelAttribute SearchVO searchVO) throws JsonProcessingException {
        log.info(">> searchVo :: " + searchVO);

        return ResponseEntity
                .ok(tradeMasterService.priceByPyung(searchVO));
    }

    @GetMapping(value = "/count/month")
    public ResponseEntity<Map<String, Object>> countByMonth(
            @ModelAttribute SearchVO searchVO) {
        log.info(">> searchVo :: " + searchVO);

        return ResponseEntity
                .ok(tradeMasterService.countByMonth(searchVO));
    }
}
