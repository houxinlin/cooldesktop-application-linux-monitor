package com.h.linuxmonitor.utils;

import com.h.linuxmonitor.bean.*;
import com.h.linuxmonitor.bean.Process;
import org.springframework.util.StringUtils;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.InternetProtocolStats;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OsUtils {
    static SystemInfo systemInfo = new SystemInfo();

    public static String byteArrayToIp(byte[] bs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bs.length; i++) {
            if (i != bs.length - 1) {
                sb.append(bs[i] & 0xff).append(".");
            } else {
                sb.append(bs[i] & 0xff);
            }
        }
        return sb.toString();
    }

    public static List<NetInfo> getNetInfos() {
        List<Process> processes = listProcess();
        return systemInfo.getOperatingSystem()
                .getInternetProtocolStats()
                .getConnections()
                .stream()
                .map(ipConnection -> createNetInfo(ipConnection)).collect(Collectors.toList());
    }

    private static NetInfo createNetInfo(InternetProtocolStats.IPConnection ipConnection) {
        return new NetInfo(ipConnection.getLocalPort(), ipConnection.getForeignPort(), ipConnection.getType(),
                ipConnection.getowningProcessId(), ipConnection.getState().toString(), byteArrayToIp(ipConnection.getLocalAddress()), byteArrayToIp(ipConnection.getForeignAddress()));
    }

    public static List<Process> listProcess() {

        List<Process> result = new ArrayList<>();
        List<OSProcess> processes = systemInfo.getOperatingSystem().getProcesses();
        processes.forEach(osProcess -> {
            if (StringUtils.hasText(osProcess.getPath())) {
                Process process = new Process();
                process.setId(osProcess.getProcessID());
                process.setName(osProcess.getName());
                process.setThreadCount(osProcess.getThreadCount());
                process.setPath(osProcess.getPath());
                process.setUserName(osProcess.getUser());
                process.setOpenFiles(osProcess.getOpenFiles());
                result.add(process);
            }

        });
        return result;
    }

    private static double format(long size) {
        BigDecimal bigDecimal = new BigDecimal(size);
        return bigDecimal
                .divide(new BigDecimal(1024), 2, RoundingMode.FLOOR)
                .divide(new BigDecimal(1024), 2, RoundingMode.FLOOR)
                .divide(new BigDecimal(1024), 2, RoundingMode.FLOOR).doubleValue();
    }

    public static Memory getMemory() {
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return new Memory(format(memory.getTotal()), format(memory.getAvailable()), format(memory.getTotal() - memory.getAvailable()));
    }

    public static List<Disk> getDiskInfo() {
        SystemInfo systemInfo = new SystemInfo();
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores(true);
        List<Disk> disks = new ArrayList<>();
        for (OSFileStore fileStore : fileStores) {
            disks.add(new Disk(fileStore.getName(), fileStore.getTotalSpace(), fileStore.getUsableSpace()));
        }
        return disks;
    }

    public static CpuInfo getCpuInfo() {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        try {
            TimeUnit.SECONDS.sleep(1);
            long[] ticks = processor.getSystemCpuLoadTicks();
            long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
            long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
            long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
            long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
            long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
            long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
            long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
            long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
            long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
            return new CpuInfo(Double.valueOf(new DecimalFormat("#.##").format(user * 1.0 / totalCpu)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
//        System.out.println("cpu核数:" + processor.getLogicalProcessorCount());
//        System.out.println("cpu系统使用率:" + );
//        System.out.println("cpu用户使用率:" + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
//        System.out.println("cpu当前等待率:" + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
//        System.out.println("cpu当前使用率:" + new DecimalFormat("#.##%").format(1.0-(idle * 1.0 / totalCpu)));
    }
}
