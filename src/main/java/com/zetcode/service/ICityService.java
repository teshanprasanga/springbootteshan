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

import java.util.List;

public interface ICityService {

    List<City> findAll();
}
