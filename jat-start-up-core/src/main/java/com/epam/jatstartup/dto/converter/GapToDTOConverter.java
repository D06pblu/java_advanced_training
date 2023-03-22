package com.epam.jatstartup.dto.converter;

import com.epam.jatstartup.dto.GapDTO;
import com.epam.jatstartup.entity.learn.Gap;
import com.epam.jatstartup.infrastructure.DTOConverter;
import org.springframework.core.convert.converter.Converter;

@DTOConverter
public class GapToDTOConverter implements Converter<Gap, GapDTO> {

    @Override
    public GapDTO convert(Gap gap) {
        String wording = gap.getName() + ": " + gap.getWording();
        return new GapDTO(wording, gap.isDone());
    }

}
