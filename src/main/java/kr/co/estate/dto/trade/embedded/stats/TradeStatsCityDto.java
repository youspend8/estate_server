package kr.co.estate.dto.trade.embedded.stats;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class TradeStatsCityDto {
    private String dong;
    private double price;
    private long count;

    public static TradeStatsCityDto of(String dong, double price, long count) {
        return new TradeStatsCityDto(dong, price, count);
    }
}
