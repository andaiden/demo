package com.mifinity.demo.adapter.repositories.user;

import com.mifinity.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(final String username);
}
