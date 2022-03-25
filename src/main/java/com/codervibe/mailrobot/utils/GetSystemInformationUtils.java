package com.codervibe.mailrobot.utils;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

/**
 * Created by Administrator on 2021/11/17  0017
 * DateTime:2021/11/17 16:57
 * Description:
 * Others:
 *
 * @author Administrator
 */

public class GetSystemInformationUtils {
    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static int cpuLoad() {
        double cpuLoad = osmxb.getSystemCpuLoad();
        return (int) (cpuLoad * 100);

    }
    public static int memoryLoad() {
        double totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
        double freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        double value = freePhysicalMemorySize/totalvirtualMemory;
        return (int) ((1-value)*100);

    }
}
