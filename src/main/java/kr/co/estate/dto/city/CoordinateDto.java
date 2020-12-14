package kr.co.estate.dto.city;

import kr.co.estate.entity.embedded.Coordinate;
import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CoordinateDto {
    private double longitude;
    private double latitude;

    public static CoordinateDto from(Coordinate coordinate) {
        return new CoordinateDto(coordinate.getLongitude(), coordinate.getLatitude());
    }

    public static CoordinateDto of(BigDecimal longitude, BigDecimal latitude) {
        return of(longitude.doubleValue(), latitude.doubleValue());
    }

    public static CoordinateDto of(double longitude, double latitude) {
        return new CoordinateDto(longitude, latitude);
    }

    /**
     * X축 경도 기준 위치 체크
     * 경도(longitude)가 지도 영역 상 좌측(right) 및 우측(left)에 해당되는지 확인
     * @param left 영역 기준 좌측 경도
     * @param right 영역 기준 우측 경도
     * @return 영역안에 위치하는가 ?
     */
    public boolean isContainAreaX(double left, double right) {
        return longitude <= right && longitude >= left;
    }

    /**
     * Y축 위도 기준 위치 체크
     * 위도(latitude)가 지도 영역 상 상단(top) 및 하단(bottom)에 해당되는지 확인
     * @param top 영역 기준 상단 위도
     * @param bottom 영역 기준 하단 위도
     * @return 영역안에 위치하는가 ?
     */
    public boolean isContainAreaY(double top, double bottom) {
        return latitude <= top && latitude >= bottom;
    }
}
