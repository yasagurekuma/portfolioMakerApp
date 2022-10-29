package com.example.PortfolioMaker.repository;


import com.example.PortfolioMaker.repository.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository <Test, Integer>{

}
