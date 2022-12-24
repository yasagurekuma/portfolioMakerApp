package com.example.PortfolioMaker.repository;

import com.example.PortfolioMaker.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMailAddressAndPassword(String email, String encPassword);

    User findByMailAddress(String mailAddress);
}
