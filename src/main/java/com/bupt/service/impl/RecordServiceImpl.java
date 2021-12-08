package com.bupt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.mapper.RecordMapper;
import com.bupt.model.dto.ChartQueryDTO;
import com.bupt.model.po.Record;
import com.bupt.model.po.User;
import com.bupt.model.vo.ChartQueryVO;
import com.bupt.service.RecordService;
import com.bupt.service.UserService;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    private UserService userService;

    @Override
    public ChartQueryVO queryChartData(ChartQueryDTO dto) {
        Integer city = dto.getCity();
        Integer community = dto.getCommunity();
        Date startTime = dto.getStartTime();
        Date endTime = dto.getEndTime();

        LambdaQueryChainWrapper<Record> wrapper = lambdaQuery();
        if (endTime != null) {
            //增加一个月
            endTime = addMonth(endTime);
            dto.setEndTime(endTime);
            wrapper = wrapper.le(Record::getDate, endTime);
        }
        if (startTime != null) {
            wrapper = wrapper.ge(Record::getDate, startTime);
        }
        List<Record> records = wrapper
                .orderByAsc(Record::getDate)
                .list();

        if (CollectionUtils.isEmpty(records)) {
            return new ChartQueryVO();
        }
        if (startTime == null) {
            dto.setStartTime(records.get(0).getDate());
        }
        if (endTime == null) {
            Date date = records.get(records.size() - 1).getDate();
            dto.setEndTime(addMonth(date));
        }

        records = records.stream()
                .filter(record -> {
                    User user = userService.getById(record.getPublishUserId());
                    if (user == null) {
                        return true;
                    }
                    boolean isCityOk = city == null || city.equals(user.getCity());
                    boolean isCommunityOk = community == null || community.equals(user.getCommunityType());
                    return isCityOk && isCommunityOk;
                })
                .collect(Collectors.toList());

        return formatData(records, dto);
    }

    @SneakyThrows
    private List<String> getMonthList(ChartQueryDTO dto) {
        List<String> monthList = Lists.newArrayList();
        String time = getMonth(dto.getStartTime());
        String endTime = getMonth(dto.getEndTime());
        while (time.compareTo(endTime) < 0) {
            monthList.add(time);

            //增加一个月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date dt = sdf.parse(time);
            time = getMonth(addMonth(dt));
        }
        return monthList;
    }

    private Date addMonth(Date date){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, 1);
        return rightNow.getTime();
    }

    private ChartQueryVO formatData(List<Record> records, ChartQueryDTO dto) {
        if (CollectionUtils.isEmpty(records)) {
            return new ChartQueryVO();
        }
        List<BigDecimal> feeList = Lists.newArrayList();
        List<Integer> orderList = Lists.newArrayList();
        List<String> monthList = this.getMonthList(dto);

        int orderCnt = 0;
        int fee = 0;

        for (String date : monthList) {
            List<Record> list = records.stream()
                    .filter(record -> StringUtils.equals(getMonth(record.getDate()), date))
                    .collect(Collectors.toList());
            for (Record record : list) {
                orderCnt++;
                fee += record.getPublishFee() + record.getResponseFee();
            }
            orderList.add(orderCnt);
            feeList.add(new BigDecimal(fee));
            orderCnt = 0;
            fee = 0;
        }
        return new ChartQueryVO()
                .setOrderList(orderList)
                .setMonthList(monthList)
                .setFeeList(feeList);
    }

    private String getMonth(Date date) {
        DateFormatter formatter = new DateFormatter("yyyy-MM");
        return formatter.print(date, Locale.ROOT);
    }
}
