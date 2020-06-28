package kr.co.estate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.entity.SearchVO;
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

    public String fetchAll(SearchVO searchVO) throws JsonProcessingException {
        List<TradeMasterDTO> list = tradeMasterRepository
                .findAllByOrderByDealDateDesc(PageRequest.of(searchVO.getPage() - 1, searchVO.getSize())).toList();

        return new ObjectMapper().writeValueAsString(list);
    }
}
