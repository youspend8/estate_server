package kr.co.estate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.entity.TradeMasterDTO;
import kr.co.estate.repository.TradeMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeMasterService {
    private final TradeMasterRepository tradeMasterRepository;

    public String fetchAll() throws JsonProcessingException {
        List<TradeMasterDTO> list = tradeMasterRepository.findAll(PageRequest.of(0, 10)).toList();

        return new ObjectMapper().writeValueAsString(list);
    }
}
