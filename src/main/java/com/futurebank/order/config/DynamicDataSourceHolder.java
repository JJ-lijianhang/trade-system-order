

package com.futurebank.order.config;

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal();
    ;

    public static void setDataSource(final String dataSource) {
        DynamicDataSourceHolder.THREAD_LOCAL.set(dataSource);
    }

    public static String getDataSource() {
        return DynamicDataSourceHolder.THREAD_LOCAL.get();
    }

    public static void clear() {
        DynamicDataSourceHolder.THREAD_LOCAL.remove();
    }


}
