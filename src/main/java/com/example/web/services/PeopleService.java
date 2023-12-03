package com.example.web.services;

import com.example.web.dto.PersonDTO;
import com.example.web.exceptions.NegativeBalance;
import com.example.web.exceptions.PersonNotFoundException;
import com.example.web.models.Instalment;
import com.example.web.models.Person;
import com.example.web.repositories.InstalmentRepository;
import com.example.web.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final InstalmentRepository instalmentRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, InstalmentRepository instalmentRepository) {
       this.peopleRepository = peopleRepository;
       this.instalmentRepository = instalmentRepository;
    }

    @Transactional
    public void save(Person person){

        enrichPerson(person);
        peopleRepository.save(person);
    }

    @Transactional
    public void updatePerson(String name, Person updatePerson){
        updatePerson.setName(name);
        peopleRepository.save(updatePerson);
    }

    public Person findById(int id){
        //System.out.println(peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new));
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public List<Person> findOneByName(String name){
//        Person personExample = new Person();
//        personExample.setName(name);
//        Example<Person> example = Example.of(personExample);
//        Optional<Person> foundPerson = peopleRepository.findOne(example);
        //peopleRepository.findByName(name).orElse(null);
        System.out.println(peopleRepository.findByName(name));

        return peopleRepository.findByName(name); // можно пееписать под возвращаемое List
    }

    public Person findByEmail(String email){
        return peopleRepository.findByEmail(email).orElseThrow(PersonNotFoundException::new);
    }

    public void load(Person person){

        //peopleRepository.findByName("Tom");
        System.out.println("testing load");
    }


    public void deletePerson(int id){
        peopleRepository.deletePersonById(id);
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

//    @Transactional
//    public Person findPerson(String name){
//
//        peopleRepository.fi
//        return null;
//    }

    private void enrichPerson(Person person){  // добавление своих данных к сущности person
        person.setCreatedAt(LocalDateTime.now());
        person.setBalance(BigDecimal.ZERO);
        person.setWallet("-");
    }

    @Transactional
    public void transfer(String emailPersonFrom, String emailPersonTo, BigDecimal amount) throws NegativeBalance {

        Person personFrom = peopleRepository.findByEmail(emailPersonFrom).orElseThrow(PersonNotFoundException::new);
        Person personTo = peopleRepository.findByEmail(emailPersonTo).orElseThrow(PersonNotFoundException::new);

        if(personFrom.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new NegativeBalance("Недостаточно средств");
        }

        personFrom.setBalance(personFrom.getBalance().subtract(amount));
        personTo.setBalance(personTo.getBalance().add(amount));
        peopleRepository.save(personFrom);
        peopleRepository.save(personTo);

    }
    @Transactional
    public void topUpBalance(String emailPerson,BigDecimal amount){
        Person person = peopleRepository.findByEmail(emailPerson).orElseThrow(PersonNotFoundException::new);
        person.setBalance(person.getBalance().add(amount)); // позже переделать метод, нужно получать подтверждение от экваринга
        peopleRepository.save(person);
    }

    @Transactional // возможно переместить этод метод в класс instalmentService
    public void buyPackageOrStartInstalment(String emailPerson, BigDecimal amountPayment) throws NegativeBalance {
        Person person = peopleRepository.findByEmail(emailPerson).orElseThrow(PersonNotFoundException::new);
        if(person.getBalance().subtract(amountPayment).compareTo(BigDecimal.ZERO) < 0){throw new NegativeBalance("Недостаточно средств");}
        person.setBalance(person.getBalance().subtract(amountPayment));

        Instalment instalment = new Instalment();  //вставить поля, описывающие параметры рассрочки
        instalmentRepository.save(instalment);
        //person.setInstalments(instalment);
        peopleRepository.save(person);
    }

    @Transactional
    public void blockedPerson(int id){
        Person person = findById(id);
        //person.setStatus(Enum.BLOCKED.getvalue()); сейчас в сущности нет статуса, потом можно добавить и сделать enum со статусами
        peopleRepository.save(person);
    }

}
