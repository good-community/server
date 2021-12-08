package com.bupt.controller;

import com.bupt.model.dto.ChartQueryDTO;
import com.bupt.model.vo.ChartQueryVO;
import com.bupt.model.vo.Response;
import com.bupt.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;

@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController {
    @Resource
    private RecordService recordService;

    @RequiresRoles("ROLE_ADMIN")
    @PostMapping("/chart")
    public Response getChartData(@RequestBody ChartQueryDTO dto) {
        log.info("query chart data, dto = {}", dto);
        ChartQueryVO vo = recordService.queryChartData(dto);
        return Response.ok(vo);
    }

    private ChartQueryVO mockData() {
        ChartQueryVO vo = new ChartQueryVO();
        vo.setMonthList(Arrays.asList("2021-3", "2021-4", "2021-5", "2021-6"));
        vo.setOrderList(Arrays.asList(3, 5, 6, 20));
        vo.setFeeList(Arrays.asList(
                new BigDecimal("33.07"),
                new BigDecimal("24.3"),
                new BigDecimal("40.34"),
                new BigDecimal("55.25")
        ));
        return vo;
    }
}
