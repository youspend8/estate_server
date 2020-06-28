package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.entity.SearchVO;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/search")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SearchController {
    private final TradeMasterService tradeMasterService;

    @GetMapping(value="/")
    public String index(
            @ModelAttribute SearchVO searchVO) throws JsonProcessingException {
        return tradeMasterService.fetchAll(searchVO);
    }
}
