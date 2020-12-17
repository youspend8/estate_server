package kr.co.estate.dto.trade;

import kr.co.estate.dto.trade.embedded.stats.TradeStatsCityDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class TradeStatsDto {
    private List<TradeStatsCityDto> cityList;
    private double priceAverage;
    private long countSum;

    public static TradeStatsDto valueOf(List<TradeStatsCityDto> list) {
        return builder()
                .cityList(list)
                .priceAverage(list.stream()
                        .mapToDouble(TradeStatsCityDto::getPrice)
                        .average()
                        .orElse(0))
                .countSum(list.stream()
                        .mapToLong(TradeStatsCityDto::getCount)
                        .sum())
                .build();
    }
}
