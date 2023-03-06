package com.example.basic.service;


import com.example.basic.entity.Role;
import com.example.basic.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role CreateNewRole(Role role){
        return roleRepository.save(role);

    }
}
