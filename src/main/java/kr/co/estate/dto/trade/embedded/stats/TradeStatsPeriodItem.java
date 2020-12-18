package kr.co.estate.dto.trade.embedded.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class TradeStatsPeriodItem {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private double price;

    private long count;

    public static TradeStatsPeriodItem of(LocalDate date, double price, long count) {
        return new TradeStatsPeriodItem(date, price, count);
    }
}
