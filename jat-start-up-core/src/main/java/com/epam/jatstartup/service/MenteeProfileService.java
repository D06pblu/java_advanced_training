package com.epam.jatstartup.service;

import com.epam.jatstartup.dto.FullMenteeDTO;

public interface MenteeProfileService {

    FullMenteeDTO findById(long id);

}
