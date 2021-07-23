/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.repository;

import com.zetcode.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author teshan.weerapperuma
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select e from User e where e.userName=:userName")
    Optional<User> findByUserName(@Param("userName") String userName);

}
