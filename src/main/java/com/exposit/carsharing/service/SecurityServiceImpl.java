package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username.toLowerCase());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            LOGGER.debug(String.format("Logged in as %s successfully.", username));
        }
    }

    @Override
    public String findLoggedInUsername() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public String getPrincipalUsername() throws UnauthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new UnauthorizedException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public UserResponse getPrincipal() throws UnauthorizedException {
        try {
            return profileService.findByEmail(getPrincipalUsername());
        } catch (EntityNotFoundException e) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public Long getPrincipalId() throws UnauthorizedException {
        return getPrincipal().getId();
    }
}
