package com.example.demo.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * description: 这里把继承的方式改为组合的方式
 * 要不然会抛出javax.management.InstanceAlreadyExistsException: MXBean already registered with name org.apache.commons.pool2:type=GenericObjectPool,name=pool
 *
 * @auth guoshuai
 * @since 2018/8/13
 */
public class FtpClientPool implements ObjectPool<FTPClient> {

    private final GenericObjectPool<FTPClient> pool;

    public FtpClientPool(FtpClientFactory ftpClientFactory){
        pool = new GenericObjectPool(ftpClientFactory);
    }

    @Override
    public FTPClient borrowObject() throws Exception, NoSuchElementException, IllegalStateException {
        return pool.borrowObject();
    }

    @Override
    public void returnObject(FTPClient obj) throws Exception {
        pool.returnObject(obj);
    }

    @Override
    public void invalidateObject(FTPClient obj) throws Exception {
        pool.invalidateObject(obj);
    }

    @Override
    public void addObject() throws Exception, IllegalStateException, UnsupportedOperationException {
        pool.addObject();
    }

    @Override
    public int getNumIdle() {
        return pool.getNumIdle();
    }

    @Override
    public int getNumActive() {
        return 0;
    }

    @Override
    public void clear() throws Exception, UnsupportedOperationException {
        pool.clear();
    }

    @Override
    public void close() {
        pool.close();
    }
}
