package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;  // 기존 User 이후에 등록될 User 의 id 계산을 위한 필드

    static {
        /**
         * users 를 static 으로 선언했기 때문에 static 블록에서 User 객체 생성 및 추가 가능
         */
        users.add(new User(1, "Kim", new Date()));
        users.add(new User(2, "Lee", new Date()));
        users.add(new User(3, "Park", new Date()));
    }

    /**
     * 모든 User 반환
     * @return
     */
    public List<User> findAll() {
        return users;
    }

    /**
     * 특정 id에 해당하는 User 반환
     * @param id
     * @return
     */
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    /**
     * User 등록
     * @param user
     * @return
     */
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

}
