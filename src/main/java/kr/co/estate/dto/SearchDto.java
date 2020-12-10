package kr.co.estate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {
    private String name;
    private String region;
    private String sigungu;
    private int page;
    private int size;
    private String sortType;
    private String sortMode;
}
