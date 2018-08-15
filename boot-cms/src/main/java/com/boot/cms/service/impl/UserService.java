package com.boot.cms.service.impl;

import com.boot.cms.dao.IUserDao;
import com.boot.cms.entity.User.UserEntity;
import com.boot.cms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/9
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserEntity queryById(int id) {
        UserEntity userEntity = userDao.getOne(id);
//        Optional<UserEntity> byId = userDao.findById(id);
//        UserEntity userEntity = byId.get();
        return userEntity;
    }
}
