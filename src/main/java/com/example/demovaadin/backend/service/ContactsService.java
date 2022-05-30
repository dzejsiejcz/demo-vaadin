package com.example.demovaadin.backend.service;

import com.example.demovaadin.backend.entity.Company;
import com.example.demovaadin.backend.entity.Contact;
import com.example.demovaadin.backend.repository.CompanyRepository;
import com.example.demovaadin.backend.repository.ContactsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactsService {
    public static final Logger LOGGER = Logger.getLogger(ContactsService.class.getName());
    private ContactsRepository contactsRepository;
    private CompanyRepository companyRepository;

    public ContactsService(ContactsRepository contactsRepository, CompanyRepository companyRepository) {
        this.contactsRepository = contactsRepository;
        this.companyRepository = companyRepository;
    }

    public List<Contact> findAll() {
        return contactsRepository.findAll();
    }

    public List<Contact> findAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return contactsRepository.findAll();
        } else {
            return contactsRepository.search(filterText);
        }

    }

    public long count() {
        return contactsRepository.count();
    }

    public void delete(Contact contact) {
        contactsRepository.delete(contact);
    }

    public void save(Contact contact) {
        if (contact == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null");
            return;
        }
        contactsRepository.save(contact);
    }

    @PostConstruct
    public void populateTestData(){
        if (companyRepository.count() ==0) {
            companyRepository.saveAll(
                    Stream.of("Gazelle", "Batavus", "Sparta")
                            .map(Company::new)
                            .collect(Collectors.toList()));
        }

        if (contactsRepository.count() == 0) {
            Random r = new Random(0);
            List<Company> companies = companyRepository.findAll();

            contactsRepository.saveAll(
                    Stream.of("Joel Kowalsky", "John Michalowski", "Patryk Drozd", "Lucas Podolsky", "Mateusz Lewandowski")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Contact contact = new Contact();
                                contact.setFirstName(split[0]);
                                contact.setLastName(split[1]);
                                contact.setCompany(companies.get(r.nextInt(companies.size())));
                                contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
                                String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName() + ".com").toLowerCase();
                                contact.setEmail(email);
                                return contact;
                            }).collect(Collectors.toList()));
        }
    }
}
