package kr.co.estate.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SearchAssistResponseDto {
    private List<SearchAssistItem> addressList;
    private List<SearchAssistItem> nameList;

    public static SearchAssistResponseDto of(List<SearchAssistItem> addressList, List<SearchAssistItem> nameList) {
        return new SearchAssistResponseDto(addressList, nameList);
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class SearchAssistItem {
        private String keyword;
        private String value;

        public static SearchAssistItem of(String value) {
            return new SearchAssistItem(value, value);
        }

        public static SearchAssistItem of(String keyword, String value) {
            return new SearchAssistItem(keyword, value);
        }
    }
}
