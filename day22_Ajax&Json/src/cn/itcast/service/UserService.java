package cn.itcast.service;

import cn.itcast.domain.User;

public interface UserService {
    User findUser(String username);
}
