package com.xcyh.xcyhtoufang.controller;

import com.alibaba.fastjson.JSON;
import com.xcyh.xcyhtoufang.pojo.Test001;
import com.xcyh.xcyhtoufang.service.TestService001;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService001 testService001;

    /**
     *
     * 获取某本书的变动章节
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTest001")
    public String getTest001(HttpServletRequest request) {

        String idStr = request.getParameter("id");
        Integer id = null;
        if(StringUtils.isNotBlank(idStr)){
            id = Integer.parseInt(idStr);
        }
        Test001 test001 = testService001.getTest001(id);
        System.out.println("action调用；；；；；；；；；；id = " + idStr);
        System.out.println("返回结果： test001 = " + JSON.toJSONString(test001));

//        KafkaConsumer<String, String> testaaaa = kafkaConsumerUtil.getConsumer();
//        System.out.println(JSON.toJSONString(kafkaConsumerUtil.getConsumer()));

//        kafkaConsumerUtil.assignByTopic(Collections.singleton("event_topic")); // 指定订阅的 topic 名
//        List<String> records;
//        while (true) {
//            records = kafkaConsumerUtil.pollRecords(1000); // 订阅数据
//            if (records.size() > 0) {
//                break;  // 直到有数据后，停止订阅
//            }
//        }
//        System.out.println(StringUtils.join(records, '\n'));

        return null;
    }

    /**
     *
     * 获取某本书的变动章节
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addTest001")
    public String addTest001(HttpServletRequest request) {

        String name = request.getParameter("name");
        if(StringUtils.isNotBlank(name)){
            Test001 test001 = new Test001();
            test001.setName(name);
            testService001.addTest001(test001);

            System.out.println("返回结果： test001 = " + JSON.toJSONString(test001));
        }

        return null;
    }

}
