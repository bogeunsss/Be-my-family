// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.blog.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String uid;

    private String name;
    private String password;
    private String email;
    private String phone;
    private String job;
    private int marriaged;  //1 = 기혼, 0 = 미혼
    private int sex;    //1 = 남자, 0 = 여자
    private String birthdate;
    private int flag;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdate;

}
