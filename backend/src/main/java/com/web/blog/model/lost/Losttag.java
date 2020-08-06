package com.web.blog.model.lost;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.internal.constraintvalidators.bv.number.sign.PositiveValidatorForBigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Losttag {
    
    @Id
    private int tagno;

    private Integer lostno;
    private String tagname;

}