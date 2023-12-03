package com.example.web.controllers;

import com.example.web.dto.PersonDTO;
import com.example.web.exceptions.models.PersonErrorResponse;
import com.example.web.models.Person;
import com.example.web.services.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addPerson(@RequestBody PersonDTO personDTO, BindingResult bindingResult){

//        if(bindingResult.hasErrors()) {
//
//        }
        peopleService.save(convertToPerson(personDTO));
//        peopleService.load(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Person> showAll(){
        return peopleService.findAll();
    }

    @GetMapping("/findByEmail/{email}")
    public PersonDTO findPersonByEmail(@PathVariable("email") String email){

        //System.out.println(peopleService.findOneByName(name));
        return convertToPersonDTO(peopleService.findByEmail(email));

        //return null;
    }

    @GetMapping("/findById/{id}")
    public PersonDTO findPersonById(@PathVariable("id") int id){

        //System.out.println(id);
        return convertToPersonDTO(peopleService.findById(id));

        //return null;
    }

    @GetMapping("/findByName/{name}")
    public List<PersonDTO> findPerson(@PathVariable("name") String name){

        //System.out.println(name);
        List<PersonDTO> listPersonDTO = new ArrayList<>();
        List<Person> listPerson = peopleService.findOneByName(name);
        listPersonDTO = listPerson.stream().map(this::convertToPersonDTO).collect(Collectors.toList());
        return listPersonDTO;

        //return null;
    }

    @PostMapping ("/transfer")
    public void transferFunds(@RequestBody PersonDTO personDTO){
        //peopleService.transfer();
    }

    private Person convertToPerson(PersonDTO personDTO){
        // конвертирование из personDTO в Person
        //ModelMapper modelMapper = new ModelMapper();
//        personC.setName(personDTO.getName());
//        personC.setEmail(personDTO.getEmail());
//        personC.setPassword(personDTO.getPassword());
        return modelMapper.map(personDTO, Person.class); // с помощью mapper установили поля из DTO в Person, поля свойст совпадают.
    }

    private PersonDTO convertToPersonDTO(Person person){
        //ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(person, PersonDTO.class);
    }

//    @GetMapping("/session")   // пока не использовать, хрен знает что делать с ней
//    public String getSession(HttpSession httpSession){
//        System.out.println("HttpSession person - " + httpSession);
//        return httpSession.getId();
//    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(){ //в скобках метода нужно указать искючение, которое ловим
        PersonErrorResponse response = new PersonErrorResponse("Person not found", System.currentTimeMillis() );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
