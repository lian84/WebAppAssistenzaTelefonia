package Service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;
import org.junit.runner.RunWith;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Test
    public void testLoadUserByUsername() {
        String username = "testuser";
        String password = "testpassword";
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetails = new User(username, password, authorities);

        // Configura il comportamento del mock per restituire userDetails quando viene chiamato il metodo loadUserByUsername
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        // Esegui il test
        UserDetails loadedUser = userDetailsService.loadUserByUsername(username);

        // Verifica che l'utente caricato abbia i dati corretti
        assertEquals(username, loadedUser.getUsername());
        assertEquals(password, loadedUser.getPassword());
        assertEquals(authorities, loadedUser.getAuthorities());
    }
}
