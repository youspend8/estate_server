package kr.co.estate.dto.trade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import kr.co.estate.common.TradeType;
import kr.co.estate.dto.city.CoordinateDto;
import kr.co.estate.dto.trade.embedded.DealDto;
import kr.co.estate.dto.trade.embedded.LocationDto;
import kr.co.estate.entity.TradeMasterEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class TradeSearchDto {
    private String uid;
    private String name;
    private Integer buildYear;
    private Integer floor;
    private Double area;
    private Double areaSub;
    private Integer amount;
    private Integer amountOption;
    private TradeType tradeType;
    private String villaType;
    private CoordinateDto coordinate;
    private DealDto deal;
    private LocationDto location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createAt;

    public long amountByPyung() {
        return Math.round((this.getAmount()) / (this.getArea() / 3.3));
    }

    public static TradeSearchDto valueOf(TradeMasterEntity entity) {
        return TradeSearchDto.builder()
                .uid(entity.getUid())
                .name(entity.getName())
                .buildYear(entity.getBuildYear())
                .floor(entity.getFloor())
                .area(entity.getArea())
                .areaSub(entity.getAreaSub())
                .amount(entity.getAmount())
                .amountOption(entity.getAmountOption())
                .tradeType(entity.getTradeType())
                .villaType(entity.getVillaType())
                .createAt(entity.getCreateAt())
                .coordinate(CoordinateDto.from(entity.getCoordinate()))
                .deal(DealDto.valueOf(entity.getDeal()))
                .location(LocationDto.valueOf(entity.getLocation()))
                .build();
    }
}
