package kr.co.estate.service;

import kr.co.estate.dto.NaverMapDto;
import kr.co.estate.entity.CityCodeEntity;
import kr.co.estate.repository.CityCodeRepository;
import kr.co.estate.repository.specification.CityCodeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityCodeService {
    private final CityCodeRepository cityCodeRepository;

    public List<CityCodeEntity> fetch(Map<String, Object> params) {
        return this.cityCodeRepository.findAll(
                CityCodeSpecification.searchBy(params));
    }

    public List<CityCodeEntity> fetchCoords(NaverMapDto naverMapDto) {
        int type = 0;
        if (naverMapDto.getZoom() >= 9) {
            type = 1;
        }
        if (naverMapDto.getZoom() >= 13) {
            type = 2;
        }
        if (naverMapDto.getZoom() >= 16) {
            type = 3;
        }
        return cityCodeRepository.findByType(type);
    }
}
