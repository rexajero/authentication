package com.townhouse.management.appuser;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDao<AppUser> {
    Optional<AppUser> get(Long id);
    Optional<AppUser> getByEmail(String email);
    Collection<AppUser> getAll();
    AppUser save(AppUser appUser);
    int enableAppUser(String email);
    int updateEmail(Long id, String email);
}
