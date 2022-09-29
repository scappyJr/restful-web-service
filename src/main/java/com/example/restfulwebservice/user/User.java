package com.example.restfulwebservice.user;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @JsonIgnoreProperties(value = {"password", "ssn"})
// @JsonFilter("UserInfo") // filter 이름을 userInfo로 지정
@Tag(name = "User", description = "사용자 상세 정보를 위한 도메인 객체")
@Entity(name = "member")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
    @Schema(description  = "사용자의 이름을 입력해주세요.")
    private String name;

    @Past
    @Schema(description  = "사용자의 등록일을 입력해주세요.")
    private Date joinDate;

    // @JsonIgnore // Getter로 데이터 반환 시 무시됨
    @Schema(description  = "사용자의 패스워드를 입력해주세요.")
    private String password;

    // @JsonIgnore
    @Schema(description  = "사용자의 주민번호를 입력해주세요.")
    private String ssn;

}
