package kr.co.estate.service;

import kr.co.estate.entity.CityCodeDTO;
import kr.co.estate.repository.CityCodeRepository;
import kr.co.estate.repository.specification.CityCodeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityCodeService {
    private final CityCodeRepository cityCodeRepository;

    public List<CityCodeDTO> fetch(Map<String, Object> params) {
        return this.cityCodeRepository.findAll(
                CityCodeSpecification.searchBy(params));
    }
}
