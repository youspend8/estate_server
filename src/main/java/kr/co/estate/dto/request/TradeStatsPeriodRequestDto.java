package kr.co.estate.dto.request;

import io.swagger.annotations.ApiParam;
import kr.co.estate.dto.query.TradeQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TradeStatsPeriodRequestDto {
    @ApiParam(value = "지역명", example = "TRADE", required = true)
    private String name;

    @ApiParam(value = "지역 코드", example = "11", required = true)
    private String region;

    @ApiParam(value = "시군구 코드", example = "170", required = true)
    private String sigungu;

    @ApiParam(value = "읍면동 코드", example = "12900", required = true)
    private String dong;

    @ApiParam(value = "지역 코드 타입", example = "1", required = true)
    private int cityType;

    @ApiParam(value = "거래 유형", example = "TRADE", required = true)
    private String tradeType;

    @ApiParam(value = "집계 시작 일시", example = "2020-10-01", required = true)
    private LocalDate fromDate;

    @ApiParam(value = "집계 종료 일시", example = "2020-11-30", required = true)
    private LocalDate toDate;

    public TradeQuery asQuery() {
        return TradeQuery.builder()
                .name(name)
                .region(region)
                .sigungu(sigungu)
                .dong(dong)
                .cityType(cityType)
                .tradeType(tradeType)
                .fromDate(fromDate)
                .toDate(toDate)
                .build();
    }
}
