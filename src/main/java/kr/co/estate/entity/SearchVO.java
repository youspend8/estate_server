package kr.co.estate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {
    private String name;
    private String region;
    private String sigungu;
    private int page;
    private int size;
    private String sortType;
    private String sortMode;
    private String tradeType;
    private String startDate;
    private String endDate;
}
