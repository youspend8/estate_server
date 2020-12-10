package kr.co.estate.dto.city;

import kr.co.estate.entity.CityCodeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CityDto {
    private String id;
    private int type;
    private String name;
    private int region;
    private int sigungu;
    private int umd;
    private String fullname;
    private CoordinateDto coordinate;

    public static CityDto from(CityCodeEntity cityCodeEntity) {
        return CityDto.builder()
                .id(cityCodeEntity.getId())
                .type(cityCodeEntity.getType())
                .name(cityCodeEntity.getName())
                .region(cityCodeEntity.getRegion())
                .sigungu(cityCodeEntity.getSigungu())
                .umd(cityCodeEntity.getUmd())
                .fullname(cityCodeEntity.getFullname())
                .coordinate(CoordinateDto.from(cityCodeEntity.getCoordinate()))
                .build();
    }
}
