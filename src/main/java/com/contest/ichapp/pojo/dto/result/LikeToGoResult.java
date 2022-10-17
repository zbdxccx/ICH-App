package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.domain.Museum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LikeToGoResult {
    private List<Museum> museumList;
}
