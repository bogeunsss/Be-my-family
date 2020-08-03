package com.web.blog.model.care;

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
public class Manager {
   
    @Id
    private String careNm;

    @JsonIgnore
    private String ManagerId;
    private String ManagerName; 
    private String ManagerPassword;
    private String ManagerEmail;
    private String ManagePhonenumber;

    @Column(insertable = false, updatable = false)
    private LocalDateTime ManagerCreatedate;

}
