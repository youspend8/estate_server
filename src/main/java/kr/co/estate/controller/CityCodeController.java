package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.city.CityDto;
import kr.co.estate.entity.CityCodeEntity;
import kr.co.estate.service.CityCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "지역 데이터")
public class CityCodeController {
    private final CityCodeService cityCodeService;

    @GetMapping(value = "/city/search")
    public ResponseEntity<ApiResponse<List<CityCodeEntity>>> search(
            @RequestParam Map<String, Object> params) {
        log.debug("params ==> {}", params);

        return ResponseEntity.ok(
                ApiResponse.valueOf(cityCodeService.fetch(params)));
}

    @GetMapping(value = "/city/search/coords")
    @ApiOperation(value = "지역 목록 조회", notes = "특정 좌표(위도, 경도)를 기준으로 Zoom Level에 따라 허용 거리에 포함되는 지역 목록 조회")
    public ResponseEntity<ApiResponse<List<CityDto>>> coords(
            @ModelAttribute NaverMapDto naverMapDto) {
        log.debug("coordinatesDto ==> {}", naverMapDto);

        return ResponseEntity.ok(
                ApiResponse.valueOf(cityCodeService.fetchCoords(naverMapDto)));
    }
}
