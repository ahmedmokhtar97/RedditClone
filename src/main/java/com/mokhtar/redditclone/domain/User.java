package com.mokhtar.redditclone.domain;

import com.mokhtar.redditclone.domain.validator.PasswordsMatch;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@PasswordsMatch
public class User implements UserDetails {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    @Size(min = 8, max = 20)
    private String email;

    @NonNull
    @Column(length = 100)
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    private String activationCode;

    @NonNull
    @NotEmpty(message = "Please Enter you First Name")
    private String firstName;

    @NonNull @NotEmpty(message = "Please Enter your last name")
    private String lastName;

    @NonNull
    @NotEmpty(message = "Enter unique alias")
    @Column(nullable = false, unique = true)
    private String alias;


    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role ->  new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void addRoles(Set<Role> roles){
        roles.forEach(this::addRole);
    }


    @Transient
    @NotEmpty(message = "Please Enter password confirm")
    private String confirmPassword;


    @OneToMany(mappedBy = "user")
    private List<Link> links;


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
