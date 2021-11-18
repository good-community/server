package com.bupt.constant;

import com.bupt.model.vo.DictVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 注册社区
 */
public enum CommunityEnum {
    /**
     * 幸福小区
     */
    XING_FU(0, "幸福小区"),
    /**
     * 汤臣一品
     */
    TANG_CHEN(1, "汤臣一品"),
    /**
     * 翻斗花园
     */
    FAN_DOU(2, "翻斗花园");

    public final Integer code;
    public final String name;

    CommunityEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        if (code == null) {
            return "";
        }
        for (CommunityEnum type : CommunityEnum.values()) {
            if (type.code.equals(code)) {
                return type.name;
            }
        }
        return "";
    }

    public static List<DictVO> getVO() {
        return Arrays.stream(CommunityEnum.values())
                .map(e -> new DictVO(e.code, e.name))
                .collect(Collectors.toList());
    }
}
