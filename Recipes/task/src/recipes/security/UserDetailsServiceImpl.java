package recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.database.user.User;
import recipes.database.user.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = null;
        user = userService.findByEmail(username) ? userService.getUserByEmail(username) : null;

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        } else {
            return new UserDetailsImpl(user);
        }
    }
}
