package com.futurebank.order.controller;


import com.futurebank.pojo.base.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/report")
public class ReportExportController {




    @PostMapping("/export")
    public CommonResp<String> export(@RequestParam("startDate") String startDate) {
        return null;
    }
}
