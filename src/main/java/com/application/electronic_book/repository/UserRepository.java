package com.application.electronic_book.repository;

import com.application.electronic_book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            nativeQuery = true,
            value = "select * from users where email = :email"
    )
    Optional<User> findUserByEmail(@Param("email") String email);
}
