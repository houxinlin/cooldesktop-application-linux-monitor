package com.h.linuxmonitor;

import com.h.linuxmonitor.bean.Disk;
import com.h.linuxmonitor.utils.OsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {
    @GetMapping("infos")
    public Map<String, Object> infos() {
        Map<String, Object> result = new HashMap<>();
        result.put("process", OsUtils.listProcess());
        result.put("memory", OsUtils.getMemory());
        result.put("cpu", OsUtils.getCpuInfo());
        result.put("net", OsUtils.getNetInfos());
        return result;
    }

    @GetMapping("/process/stop/{id}")
    public Map<String, Object> stopProcess(@PathVariable("id") int id) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", -1);
        if (id != -1) {
            try {
                java.lang.Process kill = Runtime.getRuntime().exec("kill -9 " + id);
                result.put("status", 0);
                kill.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @GetMapping("/dis/get")
    public List<Disk> getDis() {
        return OsUtils.getDiskInfo();
    }
}
