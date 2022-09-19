package com.example.restfulwebservice.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    /**
     * 생성자를 통한 의존성 주입
     * @param service
     */
    public UserController(UserDaoService service) {
        this.service = service;
    }

    /**
     * 전체 User 목록 조회
     * @return
     */
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    /**
     * 단일 User 조회
     * @param id 사용자 ID
     * @return
     */
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    /**
     * User 생성
     */
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }

}
