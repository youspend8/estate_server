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
    private int regionCode;
    private int sigunguCode;
    private int umdCode;
    private String fullname;
    private double amountAverage;
    private long count;
    private CoordinateDto coordinate;

    public static TradeAggsDto valueOf(Map<String, Object> map) {
        return TradeAggsDto.builder()
                .name(map.get("CD_NAME").toString())
                .type((int) map.get("CD_TYPE"))
                .regionCode((int) map.get("REGION_CD"))
                .sigunguCode((int) map.get("SIGUNGU_CD"))
                .umdCode((int) map.get("UMD_CD"))
                .fullname(map.get("FULLNAME").toString())
                .amountAverage(((BigDecimal) map.get("AMOUNT_AVG")).doubleValue())
                .count((long) map.get("TRADE_COUNT"))
                .coordinate(CoordinateDto.of((BigDecimal) map.get("LONGITUDE"), (BigDecimal) map.get("LATITUDE")))
                .build();
    }
}

