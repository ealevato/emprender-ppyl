package unnoba.poo2020.hotel.repository;

import unnoba.poo2020.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    // Sería hacer una consulta SQL con un SELECT * FROM users WHERE email = x
    // Va a retornar el usuario con ese mail
}
