/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.repository;

import com.zetcode.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author teshan.weerapperuma
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findByRole(String role);
}
