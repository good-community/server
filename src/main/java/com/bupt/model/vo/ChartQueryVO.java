package com.bupt.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ChartQueryVO {
    private List<BigDecimal> feeList;
    private List<Integer> orderList;
    private List<String> monthList;
}
