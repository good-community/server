package com.bupt.constant;

import com.bupt.model.vo.DictVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 城市枚举类
 */
public enum CityEnum {
    /**
     * 北京
     */
    BEIJING(0, "北京"),
    /**
     * 上海
     */
    SHANGHAI(1, "上海"),
    /**
     * 广州
     */
    GUANGZHOU(2, "广州");
    public final int code;
    public final String name;

    CityEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        if (code == null) {
            return "";
        }
        for (CityEnum city : CityEnum.values()) {
            if (city.code == code) {
                return city.name;
            }
        }
        return "";
    }

    public static List<DictVO> getVO() {
        return Arrays.stream(CityEnum.values())
                .map(e -> new DictVO(e.code, e.name))
                .collect(Collectors.toList());
    }
}
