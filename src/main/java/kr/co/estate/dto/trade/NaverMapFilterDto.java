package kr.co.estate.dto.trade;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverMapFilterDto {
    @ApiParam(value = "거래 유형", example = "TRADE", required = true)
    private String tradeType;
}
