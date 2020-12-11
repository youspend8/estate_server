package kr.co.estate.mapper;

import kr.co.estate.dto.NaverMapDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TradeMasterMapper {
    List<HashMap<String, Object>> aggregateByCity(int type, NaverMapDto naverMapDto);
}
