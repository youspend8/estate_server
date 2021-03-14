package kr.co.estate.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.LocalDate;

@Getter
@ToString
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("json-files")
public class JsonFilesProperties {
    private final String basePath;
    private final String aggregation;
    private final String search;

    public String getAggregationPath(int type, String tradeType) {
        return String.format("%s/%s/%s/%d_%s.json", basePath, aggregation, LocalDate.now(), type, tradeType);
    }

    public String getSearchAddressIndicesPath() {
        return String.format("%s/%s/%s", basePath, search, "search_address.json");
    }

    public String getSearchNameIndicesPath() {
        return String.format("%s/%s/%s", basePath, search, "search_name.json");
    }
}
