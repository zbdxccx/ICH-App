package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.domain.History;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HistoryResult {
    List<History> historyList;
}
