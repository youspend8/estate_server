package kr.co.estate.entity;

import kr.co.estate.common.TradeType;
import kr.co.estate.entity.embed.Coordinates;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="TRADE_MASTER")
public class TradeMasterDTO {
    @Id
    @Column(name="UID")
    private String uid;
    @Column(name="DEAL_YEAR")
    private Integer dealYear;
    @Column(name="DEAL_MONTH")
    private Integer dealMonth;
    @Column(name="DEAL_DAY")
    private Integer dealDay;
    @Column(name="DEAL_DATE")
    private Date dealDate;
    @Column(name="DONG")
    private String dong;
    @Column(name="JIBUN")
    private String jibun;
    @Column(name="BUILD_YEAR")
    private Integer buildYear;
    @Column(name="REGION_CD")
    private String regionCode;
    @Column(name="SIGUNGU")
    private String sigungu;
    @Column(name="FLOOR")
    private Integer floor;
    @Column(name="AREA")
    private Double area;
    @Column(name="AREA_SUB")
    private Double areaSub;
    @Column(name="AMOUNT")
    private Integer amount;
    @Column(name="AMOUNT_OPTION")
    private Integer amountOption;
    @Column(name="TRADE_TYPE")
    private TradeType tradeType;
    @Column(name="NAME")
    private String name;
    @Column(name="VILLA_TYPE")
    private String villaType;
    @Embedded
    private Coordinates coordinates;

    public long amountByPyung() {
        return Math.round((this.getAmount()) / (this.getArea() / 3.3));
    }
}
