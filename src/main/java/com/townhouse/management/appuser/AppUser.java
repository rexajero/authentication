package com.townhouse.management.appuser;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component()
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails{

    @SequenceGenerator(
        name = "app_user_sequence",
        sequenceName = "app_user_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "app_user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String password; 
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    
    public AppUser(String firstName, 
                    String lastName, 
                    String middleName,
                    Date birthday,
                    String phoneNumber,
                    String email, 
                    AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = generateInitialPassword(birthday);
        this.appUserRole = appUserRole;
        // this.locked = locked;
        // this.enabled = enabled;
    }

    public AppUser(String firstName, 
                    String lastName, 
                    String middleName,
                    Date birthday,
                    String phoneNumber,
                    String email, 
                    String password, 
                    AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        // this.locked = locked;
        // this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = 
        new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String generateInitialPassword(Date birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String pass = sdf.format(birthday);
        return pass;
    }
    
}
