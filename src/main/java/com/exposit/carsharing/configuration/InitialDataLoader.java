package com.exposit.carsharing.configuration;

import com.exposit.carsharing.domain.Role;
import com.exposit.carsharing.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private boolean alreadySetup = false;

    public InitialDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByRole(name);
        if (role == null) {
            roleRepository.save(new Role(name));
        }
    }
}