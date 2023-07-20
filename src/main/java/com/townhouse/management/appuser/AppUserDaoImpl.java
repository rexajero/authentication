package com.townhouse.management.appuser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class AppUserDaoImpl implements AppUserDao<AppUser> {

    private AppUserRepository appUserRepository;

    @Override
    public Optional<AppUser> get(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    // @Override
    // public void update(AppUser appUser) {
    //     appUserRepository.
    //     // appUserList.set(appUser.getId(), appUser);
    // }

    // @Override
    // public void delete(AppUser appUser) {
    //     appUserList.set(todo.getId(), null);
    // }
    
}
