package com.example.basic.service;


import com.example.basic.entity.Role;
import com.example.basic.entity.User;
import com.example.basic.repository.RoleRepository;
import com.example.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User RegisterNewUser( User user){

        Role role = roleRepository.findById("User").get();
        Set<Role> roles  = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepository.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin123"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepository.save(adminUser);

      User user = new User();
       user.setUserName("sahil123");
       user.setUserPassword(getEncodedPassword("sahil123"));
       user.setUserFirstName("sahil");
       user.setUserLastName("karaval");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);user.setRole(userRoles);
        userRepository.save(user);
    }

    public String getEncodedPassword(String Password){
        return passwordEncoder.encode(Password);
    }
}
