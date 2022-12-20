package com.intexsoft.apifacadeservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intexsoft.apifacadeservice.repository.RolesRepository;
import com.intexsoft.apifacadeservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service("permissionService")
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final RolesRepository rolesRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Checks if the user has the required permission.
     * @param permission the user needs
     * @return true or false
     */
    @Override
    public boolean hasPermissions(String permission) {
        return getUserNameFromKeycloakToken()
                .map(userName -> checkPermissions(userName, permission)).orElse(false);
    }

    private boolean checkPermissions(String userName, String permission) {
        try {
            Map<String, Boolean> userPermissions = findPermissionsByUserName(userName);
            return userPermissions.containsKey(permission) && userPermissions.get(permission);
        } catch (JsonProcessingException e) {
            log.error("The user has not been authorized by keycloak!");
            throw new RuntimeException(e);
        }
    }

    private Map<String, Boolean> findPermissionsByUserName(String userName) throws JsonProcessingException {
        String jsonSource = rolesRepository.findPermissionsByUserName(userName);
        return objectMapper.readValue(jsonSource, HashMap.class);
    }

    private Optional<String> getUserNameFromKeycloakToken() {
        return getKeycloakToken().map(IDToken::getPreferredUsername);
    }

    private Optional<AccessToken> getKeycloakToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken && authentication.getPrincipal() instanceof KeycloakPrincipal<?>) {
            KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            return Optional.of(principal.getKeycloakSecurityContext().getToken());
        }
        return Optional.empty();
    }
}
