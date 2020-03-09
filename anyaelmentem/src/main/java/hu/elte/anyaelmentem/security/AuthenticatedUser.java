package hu.elte.anyaelmentem.security;

import hu.elte.anyaelmentem.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthenticatedUser {
    private Users user;
}