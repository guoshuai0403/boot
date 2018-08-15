package com.boot.cms.dao;

import com.boot.cms.entity.User.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/9
 */
@Repository
@Table(name="user")
@Qualifier("userRepository")
public interface IUserDao extends JpaRepository<UserEntity, Integer> {

}
