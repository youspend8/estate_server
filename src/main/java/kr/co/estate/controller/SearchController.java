package kr.co.estate.controller;

import kr.co.estate.entity.SearchHistoryDTO;
import kr.co.estate.service.SearchHistoryService;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/search")
@CrossOrigin(value = "*", allowCredentials = "true")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final TradeMasterService tradeMasterService;
    private final SearchHistoryService searchHistoryService;

    @PostMapping(value = "/history")
    public void history(
            @CookieValue(value = "_ga") String ga,
            @RequestBody Map<String, Object> params) {
        searchHistoryService.save(ga, params);
    }

    @GetMapping(value = "/history")
    public ResponseEntity<Map<String, Object>> history(
            @CookieValue(value = "_ga") String ga) {

        return ResponseEntity.ok(searchHistoryService.fetch(ga));
    }
}
