package org.hzero.sample.app.service.impl;

import java.util.Map;

import org.hzero.boot.scheduler.api.dto.JobDataDTO;
import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * job示例
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/10 15:06
 */
@JobHandler("demo1")
public class JobServiceImpl implements IJobHandler {

    private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool tool) {
        // 任务信息
        JobDataDTO jobInfo = tool.getJobDataDTO();
        // 刷新任务进度及描述
        tool.updateProgress(50, "任务执行中...");
        // 任务日志记录
        tool.info("示例任务执行成功！");
        System.out.println("hello, " + map.get("name"));
        return ReturnT.SUCCESS;
    }

    // 下面三个没有需要可以不重写

    @Override
    public void onCreate(SchedulerTool tool) {
        // 任务开始执行
        logger.debug("Job start...");
    }

    @Override
    public void onException(SchedulerTool tool) {
        // 任务执行遇到异常
        logger.debug("Job exception...");
    }

    @Override
    public void onFinish(SchedulerTool tool, ReturnT returnT) {
        // 任务执行结束
        logger.debug("Job finish...");
    }
}
