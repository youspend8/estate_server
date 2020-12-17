package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.city.CityDto;
import kr.co.estate.service.CityCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "지역 데이터")
public class CityCodeController {
    private final CityCodeService cityCodeService;

    @GetMapping(value = "/city/search")
    @ApiOperation(value = SwaggerApiInfo.TRADE_CITY_SEARCH_VALUE, notes = SwaggerApiInfo.TRADE_CITY_SEARCH_NOTES)
    public ResponseEntity<ApiResponse<List<CityDto>>> coords(
            @ModelAttribute NaverMapDto naverMapDto) {
        log.debug("coordinatesDto ==> {}", naverMapDto);

        return ResponseEntity.ok(
                ApiResponse.valueOf(cityCodeService.search(naverMapDto)));
    }
}
