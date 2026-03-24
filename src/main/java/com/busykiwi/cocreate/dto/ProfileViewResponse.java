package com.busykiwi.cocreate.dto;

import com.busykiwi.cocreate.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileViewResponse {
    private int id;
    private String name;
    private String username;
    private String bio;
}
