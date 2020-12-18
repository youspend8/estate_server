package kr.co.estate.entity.collection;

import kr.co.estate.entity.TradeMasterEntity;

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
}