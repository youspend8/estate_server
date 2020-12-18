package kr.co.estate.entity.collection;

import kr.co.estate.entity.TradeMasterEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TradeMasterEntities {
    private final List<TradeMasterEntity> list;

    public TradeMasterEntities(List<TradeMasterEntity> list) {
        this.list = list;
    }

    public List<String> getDistinctDong() {
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

    public long filterDongDistinctCount(String dong) {
        return list.stream()
                .filter(entity -> entity.getLocation().getDong().equals(dong))
                .distinct()
                .count();
    }

    public double filterDongDistinctAmountAverage(String dong) {
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
