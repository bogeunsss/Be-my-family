package com.web.blog.model.lost;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lostreply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lostreplyno;

    private int lostno;
    private String lostreplycontent;
    private String uid;

    @Column(insertable = false, updatable = false)
    private Date createdate;
}