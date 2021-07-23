/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.service;

/**
 *
 * @author teshan.weerapperuma
 */
import com.zetcode.model.City;
import com.zetcode.repository.CityRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {

       List<City> cities = (List<City>) repository.findAll();

        return cities;
    }
}