package com.contest.ichapp.pojo.dto.param;

import com.contest.ichapp.pojo.domain.Museum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MuseumParam {
    private Integer id;
    private String name;
    private String location;
    private String description;

    public MuseumParam(Museum museum, String description) {
        this.id = museum.getId();
        this.name = museum.getName();
        this.location = museum.getLocation();
        this.description = description;
    }

}
