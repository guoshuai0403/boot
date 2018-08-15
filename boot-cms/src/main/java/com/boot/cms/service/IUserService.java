package com.boot.cms.service;

import com.boot.cms.entity.User.UserEntity;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/9
 */
public interface IUserService {

    UserEntity queryById(int id);
}
