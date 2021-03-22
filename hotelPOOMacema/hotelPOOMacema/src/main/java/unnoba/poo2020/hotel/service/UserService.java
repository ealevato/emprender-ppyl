package unnoba.poo2020.hotel.service;

import unnoba.poo2020.hotel.model.User;

import java.util.List;

public interface UserService {

    public boolean create(User user);
    public List<User> getUsers();
}
