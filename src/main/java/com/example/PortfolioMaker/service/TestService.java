package com.example.PortfolioMaker.service;

import com.example.PortfolioMaker.controller.form.TestForm;
import com.example.PortfolioMaker.repository.TestRepository;
import com.example.PortfolioMaker.repository.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    public void saveTest(TestForm testForm) {
        Test test = setTestEntity(testForm);
        testRepository.save(test);
    }
    private Test setTestEntity(TestForm testForm) {
        Test test = new Test();
        test.setId(testForm.getId());
        test.setName(testForm.getName());
        return test;
    }
}
