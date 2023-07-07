package com.cake.cakeshop.Repo;

import com.cake.cakeshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
