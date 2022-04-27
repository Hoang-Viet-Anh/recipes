package recipes.database.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
    public boolean findByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
