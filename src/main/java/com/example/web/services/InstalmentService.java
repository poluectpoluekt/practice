package com.example.web.services;

import com.example.web.dto.InstalmentDTO;
import com.example.web.exceptions.PersonNotFoundException;
import com.example.web.models.Instalment;
import com.example.web.models.Person;
import com.example.web.models.PriceShare;
import com.example.web.models.StatusInstalment;
import com.example.web.repositories.InstalmentRepository;
import com.example.web.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstalmentService {
    private final InstalmentRepository instalmentRepository;

    private final PeopleRepository peopleRepository;
    private final PriceShare priceShare;

    @Autowired
    public InstalmentService(InstalmentRepository instalmentRepository, PriceShare priceShare, PeopleRepository peopleRepository){
        this.instalmentRepository = instalmentRepository;
        this.priceShare = priceShare;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void addInstalment(InstalmentDTO instalmentDTO){

        Instalment instalment = new Instalment();
        instalment.setPriceInstallment(instalmentDTO.getPriceInstallment());
        instalment.setNumberOfPayments(instalmentDTO.getNumberOfPayments());
        instalment.setRateInstallment(priceShare.getPrice());
        instalment.setStatus(StatusInstalment.ACTIVE.getValue());
        instalment.setDateOfStart(LocalDateTime.now());
        instalment.setInstalmentPeriods(1);
        Person person = peopleRepository.findByEmail(instalmentDTO.getEmailOwner()).orElseThrow(PersonNotFoundException::new);
        instalment.setOwner(person);
        instalment.setAmount(new BigDecimal(instalment.getPriceInstallment() / priceShare.getPrice()));
        instalmentRepository.save(instalment);
    }

    @Transactional // в метод передавать полное новое значение долей для рассрочки. прибавлять к старому - нельзя
    public void updateShareAmountInstalment(Instalment updateInstalment, BigDecimal newShareAmount){
        updateInstalment.setAmount(newShareAmount);
        instalmentRepository.save(updateInstalment);

    }

    public List<Instalment> showInstalments(){
        System.out.println("test service");
        System.out.println(instalmentRepository.findAll());
        return instalmentRepository.findAll();
    }


    private void enrichInstalment(Instalment instalment, int id){


        instalment.setRateInstallment(priceShare.getPrice());
        instalment.setStatus(StatusInstalment.ACTIVE.getValue());
        instalment.setOwner(peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new));
        instalment.setDateOfStart(LocalDateTime.now());
        System.out.println("---------------");
        System.out.println(instalment);
        //System.out.println(instalment.getOwner());
        //System.out.println(idOwnerInst);
        //instalment.setOwner(peopleRepository.findById(idOwnerInst).orElseThrow(PersonNotFoundException::new));
        //Person person = peopleRepository.findByEmail(instalment.getOwner().getEmail()).orElse(null);
        //instalment.setOwner(person);
        System.out.println("---------------");
        //System.out.println(peopleRepository.findByEmail("kim.test@gmail.com").orElseThrow(PersonNotFoundException::new));
        instalmentRepository.save(instalment);
    }
}
