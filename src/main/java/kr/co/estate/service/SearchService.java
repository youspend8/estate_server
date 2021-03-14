package kr.co.estate.service;

import kr.co.estate.dto.response.SearchAssistResponseDto;

public interface SearchService {
    /**
     * 검색 자동완성 기능을 위한 검색 키워드 포함 목록 반환
     * @param keyword 검색 키워드
     * @return {@link SearchAssistResponseDto}
     */
    SearchAssistResponseDto assist(String keyword);
}
