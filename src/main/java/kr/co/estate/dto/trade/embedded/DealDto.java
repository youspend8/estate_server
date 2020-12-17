package kr.co.estate.dto.trade.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import kr.co.estate.entity.embedded.Deal;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class DealDto {
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dealDate;

    public static DealDto valueOf(Deal deal) {
        return DealDto.builder()
                .dealYear(deal.getDealYear())
                .dealMonth(deal.getDealMonth())
                .dealDay(deal.getDealDay())
                .dealDate(deal.getDealDate())
                .build();
    }
}
