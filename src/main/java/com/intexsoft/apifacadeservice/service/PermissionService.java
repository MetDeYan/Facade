package com.intexsoft.apifacadeservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PermissionService {
    /**
     * Checks if the user has the required permission.
     * @param permission the user needs
     * @return true or false
     */
    boolean hasPermissions(String permission) throws JsonProcessingException;
}
