package com.exposit.carsharing.service;

import com.exposit.carsharing.model.GeneralParameters;
import com.exposit.carsharing.repository.GeneralParametrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */

@Service
@Repository
public class GeneralParametrsService {

    @Autowired
    private GeneralParametrsRepository generalParametrsRepository;

    @PostConstruct
    @Transactional
    public void populate(){
        GeneralParameters generalParameters = new GeneralParameters();
        generalParameters.setBrand("Audi");
        generalParameters.setModel("TT");
        generalParameters.setYearOfIssue(2012);
        generalParametrsRepository.saveAndFlush(generalParameters);
    }

    @Transactional
    public List<GeneralParameters> getAll() {
        return generalParametrsRepository.findAll();
    }


    @Transactional
    public void delete(long id){
        generalParametrsRepository.delete(id);
    }

}
