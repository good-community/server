package com.bupt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.mapper.LaoninjiaMapper;
import com.bupt.mapper.RecordMapper;
import com.bupt.model.po.Laoninjia;
import com.bupt.model.po.record;
import com.bupt.service.LaoninjiaService;
import com.bupt.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl  extends ServiceImpl<RecordMapper, record> implements RecordService {
}
