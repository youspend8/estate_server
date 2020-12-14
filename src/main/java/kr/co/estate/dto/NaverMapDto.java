package kr.co.estate.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverMapDto {
    @ApiParam(value = "위도", example = "37.56516345327829", required = true)
    private double lat;
    
    @ApiParam(value = "경도", example = "126.97829443998363", required = true)
    private double lon;

    @ApiParam(value = "스크린 기준 상단 위도", example = "37.58757747876804", required = true)
    private double northLat;

    @ApiParam(value = "스크린 기준 우측 경도", example = "126.99593264692601", required = true)
    private double eastLong;

    @ApiParam(value = "스크린 기준 하단 위도", example = "37.54274268172135", required = true)
    private double southLat;

    @ApiParam(value = "스크린 기준 좌측 경도", example = "126.96065623304133", required = true)
    private double westLong;

    @ApiParam(value = "지도 줌 레벨", example = "6", required = true)
    private int zoom;

    /**
     * 지도 Zoom Level 에 따라 CityCode Type 지정
     * @return CityCode Type
     */
    public int typeByZoom() {
//        if (getZoom() >= 17) {
//            return 3;
//        }
        if (getZoom() >= 13) {
            return 2;
        }
        if (getZoom() >= 9) {
            return 1;
        }
        return 0;
    }
}
