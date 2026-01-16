package pl.szvmczek.projecthuman.domain.habitdrop;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HabitDropRepository extends CrudRepository<HabitDrop, Long> {
    List<HabitDrop> findAllByUserId(Long userId);
    Optional<HabitDrop> findByIdAndUserId(Long id, Long userId);
}
