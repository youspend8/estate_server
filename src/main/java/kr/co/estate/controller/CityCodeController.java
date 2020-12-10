package kr.co.estate.controller;

import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.entity.CityCodeEntity;
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
    public ResponseEntity<List<CityCodeEntity>> search(
            @RequestParam Map<String, Object> params) {
        log.debug("params ==> {}", params);

        return ResponseEntity.ok(cityCodeService.fetch(params));
}

    @GetMapping(value = "/search/coords")
    public ResponseEntity<?> coords(
            @ModelAttribute NaverMapDto naverMapDto) {
        log.debug("coordinatesDto ==> {}", naverMapDto);

        return ResponseEntity
                .ok(cityCodeService.fetchCoords(naverMapDto));
    }
}
