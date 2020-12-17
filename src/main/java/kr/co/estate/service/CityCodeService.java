package kr.co.estate.service;

import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.dto.city.CityDto;
import kr.co.estate.repository.CityCodeRepository;
import kr.co.estate.repository.querydsl.CityCodeRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityCodeService {
    private final CityCodeRepository cityCodeRepository;
    private final CityCodeRepositorySupport cityCodeRepositorySupport;

    @Transactional(readOnly = true)
    public List<CityDto> search(NaverMapDto naverMapDto) {
        return cityCodeRepositorySupport.findByTypeAndNaverMap(naverMapDto.typeByZoom(), naverMapDto).stream()
                .map(CityDto::from)
                .collect(Collectors.toList());
    }
}
