package kr.co.estate.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.estate.constants.SwaggerApiInfo;
import kr.co.estate.dto.response.SearchAssistResponseDto;
import kr.co.estate.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "검색기능 보조 API")
public class SearchController {
    private final SearchService searchService;

    @GetMapping(value = "/search/assist/{keyword}")
    @ApiOperation(value = SwaggerApiInfo.SEARCH_ASSIST_VALUE, notes = SwaggerApiInfo.SEARCH_ASSIST_NOTES)
    public ResponseEntity<ApiResponse<SearchAssistResponseDto>> assist(
            @PathVariable("keyword") String keyword) {
        log.debug("GET /search/assist :: request ==> keyword : {}", keyword);

        SearchAssistResponseDto result = searchService.assist(keyword);

        log.debug("GET /search/assist :: response ==> {}", result);

        return ResponseEntity.ok(
                ApiResponse.valueOf(result));
    }
}
