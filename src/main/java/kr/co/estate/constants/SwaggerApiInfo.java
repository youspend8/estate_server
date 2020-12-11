package kr.co.estate.constants;

public class SwaggerApiInfo {
    /**
     * /api/v1/trade/aggregate
     */
    public static final String TRADE_AGGREGATE_VALUE = "실거래가 지역별 집계 데이터";
    public static final String TRADE_AGGREGATE_NOTES = "특정 좌표(위도, 경도)를 기준으로 Zoom Level에 따라 허용 거리에 포함되는 지역 목록을 기준으로 실거래 내역의 집계(지역별 평균 가격, 지역별 총 거래건수 등) 값 요청";

    /**
     * /api/v1/city/search
     */
    public static final String TRADE_CITY_SEARCH_VALUE = "지역 목록 조회";
    public static final String TRADE_CITY_SEARCH_NOTES = "특정 좌표(위도, 경도)를 기준으로 Zoom Level에 따라 허용 거리에 포함되는 지역 목록 조회";
}