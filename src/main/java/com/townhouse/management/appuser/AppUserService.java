package com.townhouse.management.appuser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.townhouse.management.house.owner.Owner;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG = "User with email is not found.";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserDaoImpl appUserDaoImpl;
    private final AppUserDTOMapper appUserDTOMapper;

    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public int enableAppUser(String email) {
        return appUserDaoImpl.enableAppUser(email);
    }

    public Boolean findByEmail(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    public String resetPassword(Long id, String password) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        return "Password reset successful";
    }

    public List<AppUserDTO> findAllUsers() {
        return appUserDaoImpl.getAll().stream().map(appUserDTOMapper).collect(Collectors.toList());
    }

    public AppUserDTO findAppUserByEmail(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return appUserDTOMapper.apply(appUser);
    }

    public AppUserDTO findAppUserById(Long id) {
        AppUser appUser = appUserDaoImpl.get(id)
                            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
        return appUserDTOMapper.apply(appUser);
    }
    
    public AppUser registeAppUserFromOwner(Owner owner) {
        return appUserRepository.save(new AppUser(owner.getEmail(), generateInitialPassword(owner.getBirthday()), owner.getAppUserRole()));
    }

    public AppUserDTO register(AppUserRegistrationRequest request) {
        String pass = bCryptPasswordEncoder.encode(request.getPassword());
        AppUser appUser = appUserDaoImpl.save(new AppUser(request.getEmail(), pass, request.getAppUserRole()));

        //create token
        //send email
        
        return appUserDTOMapper.apply(appUser);
    }

    public AppUserDTO updateAppUserEmail(Long id, String email) {
        //check if new email is already used
        //check if user is registered
        //update email
        //get app user
        //map dto
        return null;
    }

    private String generateInitialPassword(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String pass = sdf.format(birthday);
        //encode password
        return pass;
    }
    
}
