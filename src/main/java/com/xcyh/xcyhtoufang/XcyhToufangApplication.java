package com.xcyh.xcyhtoufang;

import com.xcyh.xcyhtoufang.utils.TestFBJavaSDK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XcyhToufangApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcyhToufangApplication.class, args);

        TestFBJavaSDK testFBJavaSDK = new TestFBJavaSDK();
        testFBJavaSDK.test();

    }

}
