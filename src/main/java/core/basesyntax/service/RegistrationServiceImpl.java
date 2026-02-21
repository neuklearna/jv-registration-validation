package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegisterationException("User is null");
        }

        if (user.getLogin() == null || user.getLogin().length() < 6) {
            throw new RegisterationException("Login is less than 6 characters");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new RegisterationException("User already exists");
        }

        if (user.getAge() == null || user.getAge() < 18) {
            throw new RegisterationException("Age is less than 18");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new RegisterationException("Password is less than 6 characters");
        }
        return storageDao.add(user);
    }
}
