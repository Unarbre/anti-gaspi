package com.soat.anti_gaspi.infrastructure.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HTMLEmailGeneratorTestToDelete {
    @Autowired
    HTMLEmailGenerator htmlEmailGenerator;

    @Test
    void test() {
        htmlEmailGenerator.generateEmailFromTemplate(null);
    }
}