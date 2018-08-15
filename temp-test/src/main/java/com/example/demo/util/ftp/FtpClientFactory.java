package com.example.demo.util.ftp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

/**
 * description:
 * PooledObjectFactory是一个范型接口，通过实现这个接口，可以约束池中的对象如何被创建、销毁、激活、反激活。
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {

    private static Log logger = LogFactory.getLog(FtpClientFactory.class);

    private FtpPoolConfig ftpPoolConfig;

    /**
     *  创建一个新对象;当对象池中的对象个数不足时,将会使用此方法来"输出"一个新的"对象",并交付给对象池管理.
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(ftpPoolConfig.getConnectTimeOut());
        try {
            logger.info("连接ftp服务器:" +ftpPoolConfig.getHost()+":"+ftpPoolConfig.getPort());
            ftpClient.connect(ftpPoolConfig.getHost(), ftpPoolConfig.getPort());

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.error("FTPServer 拒绝连接");
                return null;
            }
            boolean result = ftpClient.login(ftpPoolConfig.getUsername(),ftpPoolConfig.getPassword());
            if (!result) {
                logger.error("ftpClient登录失败!");
                throw new Exception("ftpClient登录失败! userName:"+ ftpPoolConfig.getUsername() + ", password:"
                        + ftpPoolConfig.getPassword());
            }
            ftpClient.setControlEncoding(ftpPoolConfig.getControlEncoding());
            ftpClient.setBufferSize(ftpPoolConfig.getBufferSize());
            ftpClient.setFileType(ftpPoolConfig.getFileType());
            ftpClient.setDataTimeout(ftpPoolConfig.getDataTimeout());
            ftpClient.setUseEPSVwithIPv4(ftpPoolConfig.isUseEPSVwithIPv4());
            if(ftpPoolConfig.isPassiveMode()){
                logger.info("进入ftp被动模式");
                ftpClient.enterLocalPassiveMode();//进入被动模式
            }
        } catch (IOException e) {
            logger.error("FTP连接失败：", e);
        }
        return new DefaultPooledObject<FTPClient>(ftpClient);
    }

    /**
     * 销毁单个连接
     * 销毁对象,如果对象池中检测到某个"对象"idle的时间超时,或者操作者向对象池"归还对象"时检测到"对象"已经无效,
     * 那么此时将会导致"对象销毁";"销毁对象"的操作设计相差甚远,但是必须明确:当调用此方法时,"对象"的生命周期必须结束.
     * 如果object是线程,那么此时线程必须退出;
     * 如果object是socket操作,那么此时socket必须关闭;
     * 如果object是文件流操作,那么此时"数据flush"且正常关闭.
     * @param p
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> p) throws Exception {
        FTPClient ftpClient = p.getObject();
        // 退出登录
        ftpClient.logout();
        // 断开连接
        ftpClient.disconnect();
        // 是否连接状态
        System.out.println("FTP是否连接状态 ==== " + ftpClient.isConnected());
    }

    /**
     * 验证连接是否有效
     * 检测对象是否"有效";
     * Pool中不能保存无效的"对象",因此"后台检测线程"会周期性的检测Pool中"对象"的有效性,
     * 如果对象无效则会导致此对象从Pool中移除,并destroy;
     * 此外在调用者从Pool获取一个"对象"时,也会检测"对象"的有效性,确保不能将"无效"的对象输出给调用者;
     * 当调用者使用完毕将"对象归还"到Pool时,仍然会检测对象的有效性.
     * 所谓有效性,就是此"对象"的状态是否符合预期,是否可以对调用者直接使用;
     * 如果对象是Socket,那么它的有效性就是socket的通道是否畅通/阻塞是否超时等.
     * @param p
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> p) {
        FTPClient ftpClient = p.getObject();
        boolean connect = false;
        try {
            connect = ftpClient.sendNoOp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    /**
     * 激活单个连接
     *  "激活"对象,当Pool中决定移除一个对象交付给调用者时额外的"激活"操作,
     *  比如可以在activateObject方法中"重置"参数列表让调用者使用时感觉像一个"新创建"的对象一样;
     *  如果object是一个线程,可以在"激活"操作中重置"线程中断标记",或者让线程从阻塞中唤醒等;
     *  如果 object是一个socket,那么可以在"激活操作"中刷新通道,或者对socket进行链接重建(假如socket意外关闭)等.
     * @param p
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> p) throws Exception {
        // The default implementation is a no-op.

    }

    /**
     *  "钝化"对象,当调用者"归还对象"时,Pool将会"钝化对象";
     *  钝化的言外之意,就是此"对象"暂且需要"休息"一下.
     *  如果object是一个 socket,那么可以passivateObject中清除buffer,将socket阻塞;
     *  如果object是一个线程,可以在"钝化"操作中将线程sleep或者将线程中的某个对象wait.
     *  需要注意的时,activateObject和passivateObject两个方法需要对应,避免死锁或者"对象"状态的混乱.
     * @param p
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> p) throws Exception {
        // The default implementation is a no-op.

    }

    public FtpPoolConfig getFtpPoolConfig() {
        return ftpPoolConfig;
    }

    public void setFtpPoolConfig(FtpPoolConfig ftpPoolConfig) {
        this.ftpPoolConfig = ftpPoolConfig;
    }
}
