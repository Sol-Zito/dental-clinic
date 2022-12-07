package com.example.democlinicaodontologica.repository;

import com.example.democlinicaodontologica.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional //(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Query("from User u where u.name =:name")
    Optional<AppUser> getUserByName(@Param("name") String name);
}
