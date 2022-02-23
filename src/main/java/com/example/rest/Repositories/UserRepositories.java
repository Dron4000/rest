package com.example.rest.Repositories;

import com.example.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE u.Email = :email")
   User getUserByEmail(@Param("email")String email);
}
