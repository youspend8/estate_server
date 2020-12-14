package kr.co.estate.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ToString
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("json-files")
public class JsonFilesProperties {
    private final String basePath;
    private final String aggregation;

    public String getAggregationPath(int type) {
        return String.format("%s/%s/%d.json", basePath, aggregation, type);
    }
}
