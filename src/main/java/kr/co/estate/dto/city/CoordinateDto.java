package kr.co.estate.dto.city;

import kr.co.estate.entity.embedded.Coordinate;
import lombok.*;

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
}
