package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ProfileService profileService;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, ProfileService profileService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.profileService = profileService;
    }

    public void autoLogin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email.toLowerCase());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.info(String.format("Logged in as %s successfully.", email));
        }
    }

    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    public String getPrincipalEmail() throws UnauthorizedException {
        String email = findLoggedInEmail();
        if (email == null) {
            throw new UnauthorizedException();
        }
        return email;
    }

    @Override
    public Long getPrincipalId() throws UnauthorizedException, EntityNotFoundException {
        return profileService.findByEmail(getPrincipalEmail()).getId();
    }
}
