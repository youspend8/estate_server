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
}
