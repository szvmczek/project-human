package pl.szvmczek.projecthuman.domain.task;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
