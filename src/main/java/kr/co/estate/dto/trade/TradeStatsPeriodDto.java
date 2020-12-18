package kr.co.estate.dto.trade;

import kr.co.estate.dto.trade.embedded.stats.TradeStatsPeriodItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class TradeStatsPeriodDto {
    private List<TradeStatsPeriodItem> periodList;
    private double priceAvg;
    private long countSum;

    public static TradeStatsPeriodDto valueOf(List<TradeStatsPeriodItem> list) {
        return builder()
                .periodList(list)
                .priceAvg(list.stream()
                        .mapToDouble(TradeStatsPeriodItem::getPrice)
                        .average()
                        .orElse(0))
                .countSum(list.stream()
                        .mapToLong(TradeStatsPeriodItem::getCount)
                        .sum())
                .build();
    }
}
