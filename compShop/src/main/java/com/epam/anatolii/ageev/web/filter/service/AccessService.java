package com.epam.anatolii.ageev.web.filter.service;

import com.epam.anatolii.ageev.domain.UserRole;

public interface AccessService {
    boolean isUrlPermitted(String url);

    boolean isAccessible(UserRole userRole, String url);
}
