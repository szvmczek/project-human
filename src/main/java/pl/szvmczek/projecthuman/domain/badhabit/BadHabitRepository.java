package pl.szvmczek.projecthuman.domain.badhabit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BadHabitRepository extends CrudRepository<BadHabit, Long> {
    List<BadHabit> findAllByUserId(Long userId);
    Optional<BadHabit> findByIdAndUserId(Long id, Long userId);
}
