package com.bupt.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class ChartQueryVO {
    private List<BigDecimal> feeList;
    private List<Integer> orderList;
    private List<String> monthList;
}
