package com.epam.jatstartup.service.impl;

import com.epam.jatstartup.dto.JATInfoDTO;
import com.epam.jatstartup.dto.converter.JATInfoConverter;
import com.epam.jatstartup.repository.JATRepository;
import com.epam.jatstartup.service.JATService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JATServiceImpl implements JATService {

    private final JATRepository jatRepository;
    private final JATInfoConverter jatConverter;

    @Override
    public JATInfoDTO getGeneralInfo(int jatId) {
        return jatRepository.findById(jatId)
                .map(jatConverter::toDTO)
                .orElse(new JATInfoDTO());
    }

}
