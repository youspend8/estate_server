package kr.co.estate.entity.collection;

import kr.co.estate.entity.TradeMasterEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO : Refactor
 */
public class TradeMasterEntities {
    private final List<TradeMasterEntity> list;

    public TradeMasterEntities(List<TradeMasterEntity> list) {
        this.list = list;
    }

    public List<String> getDistinctCityBy(int cityType) {
        switch (cityType) {
            case 0: return getDistinctSigungu();
            case 1: return getDistinctDong();
        }
        return new ArrayList<>();
    }

    public List<String> getDistinctSigungu() {
        return list.stream()
                .map(entity -> entity.getLocation().getSigungu())
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> getDistinctDong() {
        return list.stream()
                .map(entity -> entity.getLocation().getDong())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDistinctDealDate() {
        return list.stream()
                .map(entity -> entity.getDeal().getDealDate())
                .distinct()
                .collect(Collectors.toList());
    }

    public long filterCityDistinctCountBy(int cityType, String city) {
        switch (cityType) {
            case 0: return filterSigunguDistinctCount(city);
            case 1: return filterDongDistinctCount(city);
        }
        return 0;
    }

    public double filterCityDistinctAmountAverageBy(int cityType, String city) {
        switch (cityType) {
            case 0: return filterSigunguDistinctAmountAverage(city);
            case 1: return filterDongDistinctAmountAverage(city);
        }
        return 0;
    }

    private long filterSigunguDistinctCount(String sigungu) {
        return list.stream()
                .filter(entity -> entity.getLocation().getSigungu().equals(sigungu))
                .distinct()
                .count();
    }

    private double filterSigunguDistinctAmountAverage(String sigungu) {
        return list.stream()
                .filter(entity -> entity.getLocation().getSigungu().equals(sigungu))
                .distinct()
                .mapToLong(TradeMasterEntity::amountByPyung)
                .average()
                .orElse(-1);
    }

    private long filterDongDistinctCount(String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .count();
    }

    private double filterDongDistinctAmountAverage(String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .mapToLong(TradeMasterEntity::amountByPyung)
                .average()
                .orElse(-1);
    }

    public long filterDealDateDistinctCount(LocalDate date) {
        return list.stream()
                .filter(entity -> entity.getDeal().getDealDate().equals(date))
                .distinct()
                .count();
    }

    public double filterDealDateDistinctAmountAverage(LocalDate date) {
        return list.stream()
                .filter(entity -> entity.getDeal().getDealDate().equals(date))
                .distinct()
                .mapToLong(TradeMasterEntity::amountByPyung)
                .average()
                .orElse(-1);
    }
}
