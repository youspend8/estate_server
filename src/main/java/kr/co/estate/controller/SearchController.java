package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/search")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    private final TradeMasterService tradeMasterService;

    @GetMapping(value="/search")
    public String index(
            @ModelAttribute SearchVO searchVO) throws JsonProcessingException {
        log.info(">> searchVo :: " + searchVO);
        return tradeMasterService.fetchAll(searchVO);
    }
}
