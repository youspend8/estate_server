package kr.co.estate.controller;

import kr.co.estate.entity.CityCodeDTO;
import kr.co.estate.service.CityCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/city")
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class CityCodeController {
    private final CityCodeService cityCodeService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<CityCodeDTO>> search(
            @RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(cityCodeService.fetch(params));
    }
}
