package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.ChosenTopicThemeDTO;
import com.epam.jatstartup.entity.learn.Theme;
import com.epam.jatstartup.infrastructure.DTOConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DTOConverter
public class ChosenThemesToDTOConverter {

    public List<ChosenTopicThemeDTO> toDTO(List<Theme> themes) {
        Map<String, List<String>> topicToThemes = resolveTopicToThemes(themes);
        return topicToThemes.entrySet().stream()
                .map(entry -> new ChosenTopicThemeDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<String, List<String>> resolveTopicToThemes(List<Theme> themes) {
        Map<String, List<String>> topicToThemes = new HashMap<>();
        for (Theme theme : themes) {
            String topicName = theme.getTopic().getName();
            List<String> namesOfThemes = topicToThemes.get(topicName);
            if (namesOfThemes == null) {
                namesOfThemes = new ArrayList<>();
            }
            namesOfThemes.add(theme.getName());
            topicToThemes.put(topicName, namesOfThemes);
        }
        return topicToThemes;
    }

}
