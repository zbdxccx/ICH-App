package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResult {
    List<Tag> tagList;
}
