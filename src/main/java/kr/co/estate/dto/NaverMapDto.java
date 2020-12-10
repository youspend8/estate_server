package kr.co.estate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverMapDto {
    private double lat;
    private double lon;
    private int zoom;
}
