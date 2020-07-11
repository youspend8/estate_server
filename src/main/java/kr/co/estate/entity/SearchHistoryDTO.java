package kr.co.estate.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "SEARCH_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SearchHistoryDTO {
    @Id
    @Column(name = "NO")
    private long no;

    @Column(name = "GA")
    private String ga;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Override
    public int hashCode() {
        return this.keyword.hashCode();
    }
}
