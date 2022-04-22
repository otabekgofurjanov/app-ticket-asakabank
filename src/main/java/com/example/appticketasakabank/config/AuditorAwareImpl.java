package com.example.appticketasakabank.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.appticketasakabank.model.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals("" + authentication.getPrincipal()))) {
            return Optional.of(((User) authentication.getPrincipal()).getId());
        }
        return Optional.empty();
    }
}