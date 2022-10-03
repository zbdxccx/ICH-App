package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgParam implements Serializable {
    private Integer height;
    private Integer width;
}
