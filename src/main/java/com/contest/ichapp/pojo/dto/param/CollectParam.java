package com.contest.ichapp.pojo.dto.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CollectParam {
    @NotNull
    private Integer collectionId;
}
