package by.andrus.riversappback.dto;

import by.andrus.riversappback.model.River;
import lombok.Data;

@Data
public class RiverDto {
    private Long id;
    private String name;
    private Integer length; // km
    private Integer poolArea; // km2
    private Integer waterConsumption;// m3/sec
    private Double averageSlope; // m/km
    private Double flowRate; // m/sec

    public static RiverDto fromRiver(River river) {
        RiverDto riverDto = new RiverDto();
        riverDto.id = river.getId();
        riverDto.name = river.getName();
        riverDto.length = river.getLength();
        riverDto.poolArea = river.getPoolArea();
        riverDto.waterConsumption = river.getWaterConsumption();
        riverDto.averageSlope = river.getAverageSlope();
        riverDto.flowRate = river.getFlowRate();
        return riverDto;
    }

    public River toRiver() {
        River river = new River();
        river.setId(id);
        river.setName(name);
        river.setLength(length);
        river.setPoolArea(poolArea);
        river.setWaterConsumption(waterConsumption);
        river.setAverageSlope(averageSlope);
        river.setFlowRate(flowRate);
        return river;
    }
}
