package com.soat.anti_gaspi.infrastructure.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HTMLEmailGeneratorTest {

    @Mock
    private SpringTemplateEngine templateEngine;

    @Mock
    private ClassLoaderTemplateResolver classLoaderTemplateResolver;

    private HTMLEmailGenerator htmlEmailGenerator;

    @BeforeEach
    void setup() {
        htmlEmailGenerator = new HTMLEmailGenerator(templateEngine, classLoaderTemplateResolver);
    }

    @Test
    void should_process_with_template_file_name_and_context() {
        Context cx1 = new Context();
        cx1.setVariable("toto", "tata");
        Context cx2 = new Context();
        cx2.setVariable("toto", "tata");


    }
}