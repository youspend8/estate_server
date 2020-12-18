package kr.co.estate.service;

import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.SearchDto;
import kr.co.estate.dto.request.TradeStatsPeriodRequestDto;
import kr.co.estate.dto.trade.*;

import java.util.List;

public interface TradeService {
    /**
     * 클라이언트의 지도 화면상에 포함되는 지역들의 실거래가 집계 데이터(지역별 평균 가격, 거래건수) 반환
     * 배치 어플리케이션을 통해 Json 형태로 저장된 파일의 데이터를 활용
     * @param naverMapDto {@link NaverMapDto}
     * @param naverMapFilterDto {@link NaverMapFilterDto}
     * @return {@link TradeAggsDto} List
     */
    List<TradeAggsDto> aggregateJson(NaverMapDto naverMapDto, NaverMapFilterDto naverMapFilterDto);

    /**
     * 클라이언트의 지도 화면상에 포함되는 지역들의 실거래가 집계 데이터(지역별 평균 가격, 거래건수) 반환
     * 실시간으로 데이터베이스로부터 데이터를 조회
     * @param naverMapDto {@link NaverMapDto}
     * @param naverMapFilterDto {@link NaverMapFilterDto}
     * @return {@link TradeAggsDto} List
     */
    List<TradeAggsDto> aggregate(NaverMapDto naverMapDto, NaverMapFilterDto naverMapFilterDto);

    /**
     * 특정 지역, 특정 기준으로의 정렬 및 페이징 처리를 하여 조건에 해당되는 실거래 내역 목록 반환
     * @param searchDto {@link SearchDto}
     * @return {@link TradeSearchDto} List
     */
    List<TradeSearchDto> search(SearchDto searchDto);

    /**
     * 특정 지역 및 필터링을 적용한 조건에 해당되는 모든 실거래 목록의 집계 정보(세부 지역별 평당가격, 거래건수) 반환
     * @param searchDto {@link SearchDto}
     * @return {@link TradeStatsDto}
     */
    TradeStatsDto stats(SearchDto searchDto);

    /**
     * 특정 기간 및 필터링을 적용한 조건에 해당되는 모든 실거래 목록의 집계 정보(세부 지역별 평당가격, 거래건수) 반환
     * @param searchDto {@link TradeStatsPeriodRequestDto}
     * @return {@link TradeStatsPeriodDto}
     */
    TradeStatsPeriodDto statsPeriod(TradeStatsPeriodRequestDto tradeStatsPeriodRequestDto);
}
