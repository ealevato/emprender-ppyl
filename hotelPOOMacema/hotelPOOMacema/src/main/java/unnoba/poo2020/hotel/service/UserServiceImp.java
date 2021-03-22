package unnoba.poo2020.hotel.service;

import unnoba.poo2020.hotel.model.User;
import unnoba.poo2020.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    // Cuando creamos un User, en vez de retornar el usuario creado, va a retornar un boolean confirmando o no el registro
    @Override
    public boolean create(User user) {
        if(repository.findByEmail(user.getEmail())==null){
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user = repository.save(user);
            return true; // Se creo exitosamente el usuario
        }
        else {
            return false; // Ese usuario ya está creado - Verifica el email
        }
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }
}
