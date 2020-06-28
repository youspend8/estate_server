package kr.co.estate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO {
    private String name;
    private int page;
    private int size;
}
