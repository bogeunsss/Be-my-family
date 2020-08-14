package com.web.blog.model.postscript;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Postpic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postpicno;

    private int postscriptno;
    private String postpath;
}