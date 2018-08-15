package com.example.demo.util.ftp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpUtilTest {

    @Test
    public void uploadFile() throws Exception {
        File file = new File("C:/Users/40805/Desktop/TIM图片20180727175133.png");
        InputStream inputStream = new FileInputStream(file);
        for (int i = 0; i < 3; i++) {
            int aa = i;
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String filepath = "/" + aa;
                                FtpUtil.uploadFile("/media/file", filepath, "asdf.png", inputStream);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
        }
    }

}