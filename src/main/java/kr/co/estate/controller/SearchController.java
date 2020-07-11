package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.service.SearchHistoryService;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(value="/search")
    public String index(
            @ModelAttribute SearchVO searchVO) throws JsonProcessingException {
        log.info(">> searchVo :: " + searchVO);
        return tradeMasterService.fetchAll(searchVO);
    }

    @PostMapping(value = "/history")
    public void history(
            @CookieValue(value = "_ga") String ga,
            @RequestBody Map<String, Object> params) {
        searchHistoryService.save(ga, params);
    }
}
