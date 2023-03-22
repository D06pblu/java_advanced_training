package com.epam.jatstartup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChosenTopicThemeDTO {

    private String topic;
    private List<String> themes;

}
