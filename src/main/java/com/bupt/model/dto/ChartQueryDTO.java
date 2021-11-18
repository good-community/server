package com.bupt.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ChartQueryDTO {
    @JsonFormat(pattern = "yyyy-MM")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM")
    private Date endTime;
    private Integer city;
    private Integer community;
}
