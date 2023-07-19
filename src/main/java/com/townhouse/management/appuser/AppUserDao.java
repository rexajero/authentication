package com.townhouse.management.appuser;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDao<AppUser> {
    Optional<AppUser> get(Long id);
    Collection<AppUser> getAll();
    AppUser save(AppUser appUser);
    // void update(AppUser appUser);
    // void delete(AppUser appUser);
}
