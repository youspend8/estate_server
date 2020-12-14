package kr.co.estate.dto.trade;

import kr.co.estate.dto.city.CoordinateDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class TradeAggsDto {
    private String name;
    private int type;
    private String regionCode;
    private String sigunguCode;
    private String umdCode;
    private String fullname;
    private double amountAverage;
    private long count;
    private CoordinateDto coordinate;

    public static TradeAggsDto valueOf(Map<String, Object> map) {
        return TradeAggsDto.builder()
                .name(map.get("CD_NAME").toString())
                .type((int) map.get("CD_TYPE"))
                .regionCode((String) map.get("REGION_CD"))
                .sigunguCode((String) map.get("SIGUNGU_CD"))
                .umdCode((String) map.get("UMD_CD"))
                .fullname(map.get("FULLNAME").toString())
                .amountAverage(((BigDecimal) map.get("AMOUNT_AVG")).doubleValue())
                .count((long) map.get("TRADE_COUNT"))
                .coordinate(CoordinateDto.of((BigDecimal) map.get("LONGITUDE"), (BigDecimal) map.get("LATITUDE")))
                .build();
    }

    /**
     * 경도(longitude)가 지도 영역 상 좌측(right) 및 우측(left)에 해당되는지 확인
     * @param top 영역 기준 상단 위도
     * @param bottom 영역 기준 하단 위도
     * @param left 영역 기준 좌측 경도
     * @param right 영역 기준 우측 경도
     * @return 영역 안에 위치하는가 ?
     */
    public boolean isContainArea(double top, double bottom, double left, double right) {
        return coordinate.isContainAreaX(left, right) && coordinate.isContainAreaY(top, bottom);
    }
}

