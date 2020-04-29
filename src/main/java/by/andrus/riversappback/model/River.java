package by.andrus.riversappback.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rivers")
@Data
public class River extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "length")
    private Integer length;

    @Column(name = "pool_area")
    private Integer poolArea;

    @Column(name = "water_consumption")
    private Integer waterConsumption;

    @Column(name = "average_slope")
    @Nullable
    private Double averageSlope;

    @Column(name = "flow_rate")
    private Double flowRate;
}
