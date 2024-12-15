package org.dreamsofspring.tasks.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.dreamsofspring.tasks.mapper.UserDto;
import org.dreamsofspring.tasks.repository.FbiRepository;
import org.dreamsofspring.tasks.repository.UserRepository;
import org.springframework.stereotype.Service;

interface UserServiceInterface {
    UserDto getUserWithTasks(Long userId);

    Object user();

    void dropAgent(Long id);
};

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final FbiRepository fbiRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, FbiRepository fbiRepository) {
        this.userRepository = userRepository;
        this.fbiRepository = fbiRepository;
    }

    @Override
    public UserDto getUserWithTasks(Long userId) {
        var user = userRepository
                .findUserWithTasksAndFbi(userId)
                .orElseThrow(() -> new GlobalExceptionHandler.ResourceNotFoundException("User with this id is not found"));

        return new UserDto(user.getName(), user.getTasks().stream().toList(), user.getFbiAgents().stream().toList());
    }

    @Override
    public Object user() {
        return this.userRepository.user();
    }

    @Override
    @Transactional
    public void dropAgent(Long id) {
        this.fbiRepository.deleteFbiEntityById(id);
    }
}
