package com.web.blog.model.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Manager {
   
    @Id
    private String mid;

    private String careNM;
    private String name; 
    private String password;
    private String email;
    private String phone;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdate;

}
