package recipes.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.database.user.User;
import recipes.database.user.UserService;

import javax.validation.Valid;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Gson gson;

    @PostMapping("/api/register")
    ResponseEntity<String> registerUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
        } else if (userService.findByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            user = userService.registerUser(user);
            return new ResponseEntity<>(gson.toJson(user.toJsonObject()), HttpStatus.OK);
        }
    }
}
