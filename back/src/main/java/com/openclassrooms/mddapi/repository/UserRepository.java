package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DBUser, Long> {
    Optional<DBUser> findByEmail(String email);
}
