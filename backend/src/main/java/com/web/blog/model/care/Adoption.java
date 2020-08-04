package com.web.blog.model.care;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Adoption {
    
    @Id
    private int adoptionno;

    @JsonIgnore
    private String uid;
    private String mid;
    private String desertionno;
    // 시간, 날짜, 이메일


}