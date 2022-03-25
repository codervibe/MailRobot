package com.codervibe.mailrobot.utils.common;

import com.codervibe.mailrobot.utils.GetSystemInformationUtils;
import com.codervibe.mailrobot.utils.MailUtils;
import com.codervibe.mailrobot.utils.RedisUtils;
import com.codervibe.mailrobot.utils.WeatherUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2022/3/15  0015
 * DateTime:2022/03/15 22:35
 * Description: 定时发送邮件
 * Others:
 *
 * @author Administrator
 */
@Component
public class SendEmailsRegularly {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy—MM—dd HH:mm:ss");
    @Resource
    RedisUtils redisUtils;
    /**
     * 日志管理 cLogger.info("输出info的log日志最低级别需要设置为INFO");
     * cLogger.warn("输出warn的log日志最低级别需要设置为WARN");
     */
    Log log = LogFactory.getLog(this.getClass());


    @Resource
    MailUtils mailUtils;

    /**
     * 获取天气并将其存储在 Redis 中
     */
    @Scheduled(cron = "0 5 5 * * *")
    @Scheduled(cron = "0 0 14 * * *")
    public void getTheWeatherAndStoreItInRedis() {

        log.info("天气 储存的key = " + SDF.format(new Date()));

        boolean whetherToStoreInRedis =redisUtils.setStr("" + SDF.format(new Date()), WeatherUtils.getWeather("高唐"));
        if (whetherToStoreInRedis) {
            log.info("已存入redis数据库中");
            log.info("Key:"+ SDF.format(new Date())+ "value:"+ redisUtils.getStr(SDF.format(new Date())));
            log.info(redisUtils.exists(SDF.format(new Date())));
        }
    }

    /**
     * 获取天气并将其存储在 Redis 中
     */
    @Scheduled(cron = "0 15 5 * * *")
    @Scheduled(cron = "0 5 14 * * *")
    @Scheduled(cron = "0 5 17 * * *")
    @Scheduled(cron = "0 10 23 * * *")
    public void determineWhetherTheWeatherInformationHasBnStoredInRedis() {

        boolean whetherThereIsTodayWeather = redisUtils.exists("" + SDF.format(new Date()));
        if (whetherThereIsTodayWeather) {
            log.info("天气信息已经存入redis 中可以自由调取");
            System.out.println("_______________________________________");
            log.info("" + redisUtils.getStr("" + SDF.format(new Date())));
        } else {
            log.info("天气信息没有存入redis,请查看日志");
        }
    }

    /**
     * 服务器启动的时候 发送一个邮件
     */
    @PostConstruct
    public void aMessageIsSentWhenTheServerStarts() {
        SimpleDateFormat timeFormatForSendingMail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String text = timeFormatForSendingMail.format(new Date()) + "\t" + "这个邮件用来测试";
        redisUtils.set("测试中....", "测试完成");
        Object value = redisUtils.get("测试中....");
        boolean valueinredisstatus=value!=null;
        log.info("value !=null 为 " + valueinredisstatus);
        if (value != null) {
            text += "redis 服务准备完毕!";
            text += "测试完毕后 删除键值对" + "\t";
            redisUtils.remove("测试中....");
        } else {
            text += "redis服务准备失败!";
            text += "请查看日志............";
        }
        text += "此邮件在服务启动后发送............";


        mailUtils.sendSimpleMail("3164866298@qq.com", "来自邮件机器人的测试邮件", text);
        log.info("邮件信息 = " + text);


    }

    /**
     * 将服务器当前信息发送到我的邮箱
     */
    @Scheduled(cron = "0 20 5 * * *")
    @Scheduled(cron = "0 10 14 * * *")
    @Scheduled(cron = "0 18 17 * * *")
    @Scheduled(cron = "0 13 23 * * *")
    public void sendTheCurrentInformationOfTheServerToMyMailbox() {
        // 获取当前cpu  内存的占用情况
        int cpuLoad = GetSystemInformationUtils.cpuLoad();
        int memoryLoad = GetSystemInformationUtils.memoryLoad();
        SimpleDateFormat timeForSendingMail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String text =timeForSendingMail.format(new Date()) + "当前cpu占用"+ cpuLoad+ "%"+ "当前内存占用"+ memoryLoad+ "%";

        boolean whetherThereIsTodaysWeather = redisUtils.exists(SDF.format(new Date()));

        if (whetherThereIsTodaysWeather) {
            text += "数据库中有相应的数据";

            text += "\n" + redisUtils.getStr(SDF.format(new Date()).replace("\t", "\n"));

        } else {
            text += "数据库中没有相应的数据 请查看日志";
        }
        log.info("现在时间：" + new Date());
        mailUtils.sendSimpleMail("3164866298@qq.com", "来自邮件机器人的天气邮件", text);

        log.info("邮件信息是\n" + text);

    }
}
