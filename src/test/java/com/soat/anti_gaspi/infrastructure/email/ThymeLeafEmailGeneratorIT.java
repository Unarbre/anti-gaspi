package com.soat.anti_gaspi.infrastructure.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ThymeLeafEmailGeneratorIT {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private EmailThymeLeafContextFactory emailThymeLeafContextFactory;

    private ThymeLeafEmailGenerator htmlEmailGenerator;

    @BeforeEach
    void setup() {
        htmlEmailGenerator = new ThymeLeafEmailGenerator(templateEngine, emailThymeLeafContextFactory);
    }

    @Test
    void should_process_with_template_file_name_and_context() {
        var parameters = new OfferConfirmationParameters(
                "a title",
                "a description",
                "http://validation.com",
                "http/deletion.com"
        );
        var result = htmlEmailGenerator.generateEmailFromTemplate(parameters);

        var expectedOutput = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Email</title>
                    <style>
                                
                        .validation-button, .deletion-button {
                            margin: 10px;
                            color: white;
                            border: none;
                            padding: 10px;
                        }
                        .validation-button {
                            background-color: deepskyblue;
                        }
                                
                        .deletion-button {
                            background-color: indianred;
                        }
                                
                        button{
                            ba: red;
                        }
                        .button-container {
                            margin: 10px;
                        }
                                
                        .text-bold {
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                <div>
                    <div>
                        <p>Bonjour <span ></span>,</p>
                        <p>Voici le résumé de votre annonce Anti Gaspi</p>
                    </div>
                    <div>
                        <p>Titre : <span class="text-bold">title1</span></p>
                        <p>Description : <span>description1</span></p>
                    </div>
                                
                    <div class="button-container">
                        <a href="http://localhost:8080/validate/1">
                            <button type="button" class="validation-button">Valider l'annonce</button>
                        </a>
                        <a href="">
                            <button type="button" class="deletion-button">Supprimer l'annonce</button>
                        </a>
                    </div>
                                
                    <div>
                        <p>Cordialement,</p>
                        <p>L'équipe Anti Gaspi</p>
                    </div>
                </div>
                                
                </body>
                </html>""";
        assertThat(result).isEqualTo(expectedOutput);
    }
}