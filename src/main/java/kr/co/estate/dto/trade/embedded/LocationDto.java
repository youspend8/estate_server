package kr.co.estate.dto.trade.embedded;

import kr.co.estate.entity.embedded.Location;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class LocationDto {
    private String sigungu;
    private String dong;
    private String jibun;
    private String regionCode;
    private String sigunguCode;
    private String umdCode;

    public static LocationDto valueOf(Location location) {
        return LocationDto.builder()
                .sigungu(location.getSigungu())
                .dong(location.getDong())
                .jibun(location.getJibun())
                .regionCode(location.getRegionCode())
                .sigunguCode(location.getSigunguCode())
                .umdCode(location.getUmdCode())
                .build();
    }
}
