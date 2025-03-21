package com.futurebank.order.enums;

/**
 * @author ben
 * @date 2024/3/25 10:45
 * 轮询策略：15s/15s/30s/3m/10m/20m/30m/30m/30m/60m/3h/3h/3h/6h/6h - 总计 24h4m
 * rocket策略：1s(1) 5s(2) 10s(3) 30s(4) 1m(5) 2m(6) 3m(7) 4m(8) 5m(9) 6m(10) 7m(11) 8m(12) 9m(13) 10m(14) 20m(15) 30m(16) 1h(17) 2h(18)
 **/
public enum DelayEnum {

    间隔10S(3, 10 * 1000, "1th interval 10s"),
    间隔10S1(3, 10 * 1000, "2th interval 10s"),
    间隔30S(4, 30 * 1000, "interval 30s"),
    间隔3M(7, 60 * 1000 * 3, "interval 3m"),
    间隔10M(14, 60 * 1000 * 10, "interval 10m"),
    间隔20M(15, 60 * 1000 * 20, "interval 20m"),
    间隔30M(16, 60 * 1000 * 30, "1th interval 30m"),
    间隔30M1(16, 60 * 1000 * 30, "2th interval 30m"),
    间隔30M2(16, 60 * 1000 * 30, "3th interval 30m"),
    间隔60M(17, 60 * 1000 * 60, "interval 60m"),
    间隔2H(18, 60 * 1000 * 60 * 2, "1th interval 2h"),
    间隔2H1(18, 60 * 1000 * 60 * 2, "2th interval 2h"),
    间隔2H2(18, 60 * 1000 * 60 * 2, "3th interval 2h"), //9 hour
    间隔2H3(18, 60 * 1000 * 60 * 6, "4th interval 2h"),
    间隔2H4(18, 60 * 1000 * 60 * 6, "5th interval 2h"),
    间隔2H5(18, 60 * 1000 * 60 * 6, "6th interval 2h"),
    间隔2H6(18, 60 * 1000 * 60 * 6, "7th interval 2h"),
    间隔2H7(18, 60 * 1000 * 60 * 6, "9th interval 2h"),
    间隔2H8(18, 60 * 1000 * 60 * 6, "10th interval 2h"),
    间隔2H9(18, 60 * 1000 * 60 * 6, "12th interval 2h"),
    间隔2H10(18, 60 * 1000 * 60 * 6, "13th interval 2h")//25
    ;


    private int code;
    private long interval;
    private String description;

    DelayEnum(int code, long interval, String description) {
        this.code = code;
        this.interval = interval;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取当前的枚举
     *
     * @param code
     * @return
     */
    public static DelayEnum getByCode(int code) {
        for (DelayEnum e : DelayEnum.values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null; // 如果找不到对应的枚举对象，则返回 null 或者抛出异常
    }

    /**
     * 获取下一个枚举
     *
     * @return
     */
    public DelayEnum getNextEnum() {
        DelayEnum[] enums = DelayEnum.values();
        int currentIndex = this.ordinal();
        int nextIndex = (currentIndex + 1) % enums.length;
        return enums[nextIndex];
    }

    public static DelayEnum getNextEnum(int code) {
        DelayEnum[] enums = DelayEnum.values();
        int index = code + 1;
        if (index >= enums.length) {
            return null;
        }
        return enums[index];
    }

    public static void main(String[] args) {
//        System.out.println(getByCode(3).getNextEnum().getDescription());
        System.out.println(DelayEnum.getNextEnum(0));
    }


}
