package com.autobots.automanager.Selecionar;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Email;

@Component
public class EmailSelecionador {

    public Email selecionar(List<Email> emails, long id) {
		
        for (Email email : emails) {

            if (email.getId() == id) {

                return email; // Return the email as soon as it's found

            }
        }

        return null; // Return null if no email is found

    }
}
