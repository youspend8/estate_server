package kr.co.estate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.estate.service.TradeMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/search")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SearchController {
    private final TradeMasterService tradeMasterService;

    @GetMapping(value="/")
    public String index() throws JsonProcessingException {
        return tradeMasterService.fetchAll();
    }
}
