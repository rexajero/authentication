package com.townhouse.management.appuser;

public record AppUserDTO(
    Long id,
    String email,
    boolean locked,
    boolean enabled,
    AppUserRole appUserRole
) {
    
}
