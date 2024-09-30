package com.example.Fleet.Management.API.repository;

import com.example.Fleet.Management.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByIdOrEmail(Integer id, String email);

    Optional<User> findByEmail(String uid);
}
