package com.bupt.controller;

import com.bupt.model.dto.ChartQueryDTO;
import com.bupt.model.vo.ChartQueryVO;
import com.bupt.model.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController {
    @PostMapping("/chart")
    public Response getChartData(@RequestBody ChartQueryDTO dto) {
        log.info("query chart data, dto = {}", dto);
        ChartQueryVO vo = new ChartQueryVO();
        vo.setMonthList(Arrays.asList("2021-3", "2021-4", "2021-5", "2021-6"));
        vo.setOrderList(Arrays.asList(3, 5, 6, 20));
        vo.setFeeList(Arrays.asList(
                new BigDecimal("33.07"),
                new BigDecimal("24.3"),
                new BigDecimal("40.34"),
                new BigDecimal("55.25")
        ));
        return Response.ok(vo);
    }
}
