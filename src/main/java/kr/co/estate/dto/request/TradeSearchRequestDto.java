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
public class TradeSearchRequestDto {
    @ApiParam(value = "지역명", example = "TRADE", required = true)
    private String name;

    @ApiParam(value = "지역 코드", example = "11", required = true)
    private String region;

    @ApiParam(value = "시군구 코드", example = "170", required = true)
    private String sigungu;

    @ApiParam(value = "조회 페이지(Paging Offset)", example = "0", required = true)
    private int page;

    @ApiParam(value = "페이지당 조회할 건수(Paging Limit)", example = "10", required = true)
    private int size;

    @ApiParam(value = "정렬 기준", example = "AMOUNT", required = true)
    private String sortType;

    @ApiParam(value = "정렬 모드", example = "desc", required = true)
    private String sortMode;

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
                .page(page)
                .size(size)
                .sortMode(sortMode)
                .sortType(sortType)
                .tradeType(tradeType)
                .fromDate(fromDate)
                .toDate(toDate)
                .isPaging(true)
                .build();
    }
}
