package com.example.demo.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/14
 */
@Component
public class FtpUtil {
    

    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private static FtpClientPool ftpClientPool;

    /** 留着让spring注入用 */
    @Autowired
    public void setFtpClientPool(FtpClientPool ftpClientPool) {
        FtpUtil.ftpClientPool = ftpClientPool;
    }

    public static boolean uploadFile(String basePath, String filePath, String remote, InputStream inputStream) throws Exception {
        FTPClient client=null;
        try {
            client=	ftpClientPool.borrowObject();
            //切换到上传目录
            if (!client.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!client.changeWorkingDirectory(tempPath)) {
                        if (!client.makeDirectory(tempPath)) {
                            return false;
                        } else {
                            client.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            return client.storeFile(remote, inputStream);
        }finally{
            ftpClientPool.returnObject(client);
        }
    }
}
