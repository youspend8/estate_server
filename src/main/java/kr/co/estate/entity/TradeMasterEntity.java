package kr.co.estate.entity;

import kr.co.estate.common.TradeType;
import kr.co.estate.entity.embedded.Coordinate;
import kr.co.estate.entity.embedded.Deal;
import kr.co.estate.entity.embedded.Location;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TRADE_MASTER")
public class TradeMasterEntity {
    @Id
    @Column(name = "UID")
    private String uid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BUILD_YEAR", nullable = false)
    private Integer buildYear;

    @Column(name = "FLOOR", length = 3)
    private Integer floor;

    @Column(name = "AREA", nullable = false)
    private Double area;

    @Column(name = "AREA_SUB")
    private Double areaSub;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name = "AMOUNT_OPTION")
    private Integer amountOption;

    @Column(name = "TRADE_TYPE", nullable = false, length = 1)
    private TradeType tradeType;

    @Column(name = "VILLA_TYPE")
    private String villaType;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createAt;

    @Embedded
    private Coordinate coordinate;

    @Embedded
    private Deal deal;

    @Embedded
    private Location location;

//    @Column(name = "DISTANCE", insertable = false, updatable = false)
//    private Double distance;

    public long amountByPyung() {
        return Math.round((this.getAmount()) / (this.getArea() / 3.3));
    }
}
