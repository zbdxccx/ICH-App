package com.contest.ichapp.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CollectParam {
    @NotNull
    private Integer collectionId;
}
