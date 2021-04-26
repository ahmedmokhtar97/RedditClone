package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByEmailAndActivationCode(String email, String activationCode);

}
