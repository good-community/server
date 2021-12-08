package com.bupt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bupt.model.dto.ChartQueryDTO;
import com.bupt.model.po.Record;
import com.bupt.model.vo.ChartQueryVO;

public interface RecordService extends IService<Record> {
     ChartQueryVO queryChartData(ChartQueryDTO dto);
}
