package com.bupt.constant;

public enum CommunityType {
    /**
     * 劳您驾
     */
    REQUESTER(0, "劳您驾"),
    /**
     * 我可以
     */
    HELPER(1, "我可以");

    public final Integer code;
    public final String name;

    CommunityType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        if (code == null) {
            return "";
        }
        for (CommunityType type : CommunityType.values()) {
            if (type.code.equals(code)) {
                return type.name;
            }
        }
        return "";
    }
}
