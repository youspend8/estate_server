package kr.co.estate.dto.query;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeQuery {
    private String name;
    private String region;
    private String sigungu;
    private String dong;
    private int cityType;
    private int page;
    private int size;
    private String sortType;
    private String sortMode;
    private String tradeType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean isPaging;
}
