package kr.co.estate.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverMapDto {
    @ApiParam(value = "위도", example = "37.254623441", required = true)
    private double lat;
    
    @ApiParam(value = "경도", example = "124.552394722", required = true)
    private double lon;
    
    @ApiParam(value = "지도 줌 레벨", example = "13", required = true)
    private int zoom;
}
