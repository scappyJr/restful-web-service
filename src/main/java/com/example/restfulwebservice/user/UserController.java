package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private UserDaoService service;

    /**
     * 생성자를 통한 의존성 주입
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
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // HATEOAS 적용
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    /**
     * User 생성
     * @param user 사용자 정보
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * User 삭제
     * @param id 사용자 ID
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

    /**
     * User 수정
     * @param user 사용자 정보
     * @return
     */
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = service.update(id, user);

        if (updatedUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }

        return updatedUser;
    }

}
