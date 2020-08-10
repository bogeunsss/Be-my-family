package com.web.blog.model.lost;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Valid
@Data
public class LostPic {

    List<MultipartFile> lostPics;
    
}
