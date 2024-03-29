package com.townhouse.management.appuser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AppUserDaoImpl implements AppUserDao<AppUser> {

    private final AppUserRepository appUserRepository;

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

    @Override
    public Optional<AppUser> getByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    @Override
    public int updateEmail(Long id, String email) {
        return appUserRepository.updateEmail(id, email);
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
