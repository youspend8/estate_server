package kr.co.estate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.estate.config.properties.JsonFilesProperties;
import kr.co.estate.dto.response.SearchAssistResponseDto;
import kr.co.estate.dto.search.SearchNameIndicesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final ObjectMapper objectMapper;
    private final JsonFilesProperties jsonFilesProperties;

    @Override
    public SearchAssistResponseDto assist(String keyword) {
        try {
            List<String> resultAddressIndices = objectMapper.readValue(new File(
                    jsonFilesProperties.getSearchAddressIndicesPath()), new TypeReference<List<String>>() {});

            List<SearchNameIndicesDto> resultNameIndices = objectMapper.readValue(new File(
                    jsonFilesProperties.getSearchNameIndicesPath()), new TypeReference<List<SearchNameIndicesDto>>() {});

            return SearchAssistResponseDto.of(
                    resultAddressIndices.stream()
                            .filter(item -> item.contains(keyword))
                            .limit(5)
                            .map(SearchAssistResponseDto.SearchAssistItem::of)
                            .collect(Collectors.toList()),
                    resultNameIndices.stream()
                            .filter(item -> item.getName().contains(keyword))
                            .limit(5)
                            .map(item -> SearchAssistResponseDto.SearchAssistItem.of(item.getName(), item.getAddress()))
                            .collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
