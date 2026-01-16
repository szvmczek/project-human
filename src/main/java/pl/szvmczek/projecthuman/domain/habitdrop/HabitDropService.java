package pl.szvmczek.projecthuman.domain.habitdrop;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropDto;
import pl.szvmczek.projecthuman.domain.user.User;
import pl.szvmczek.projecthuman.domain.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitDropService {
    private final HabitDropRepository habitDropRepository;
    private final UserService userService;

    public HabitDropService(HabitDropRepository habitDropRepository, UserService userService) {
        this.habitDropRepository = habitDropRepository;
        this.userService = userService;
    }

    public void create(HabitDropDto dto, Long userId){
        User user = userService.getReferenceById(userId);
        HabitDrop habit = HabitDropDtoMapper.map(dto);
        habit.setUser(user);
        habitDropRepository.save(habit);
    }

    public List<HabitDropDto> getAll(Long userId){
        return habitDropRepository.findAllByUserId(userId)
                .stream()
                .map(HabitDropDtoMapper::map)
                .toList();
    }

    @Transactional
    public void update(HabitDropDto dto, Long userId){
        HabitDrop originalHabit = getHabitOrThrow(dto.getId(), userId);
        originalHabit.setTitle(dto.getTitle());
        originalHabit.setDescription(dto.getDescription());
    }

    public void delete(Long habitId, Long userId){
        HabitDrop habit = getHabitOrThrow(habitId, userId);
        habitDropRepository.delete(habit);
    }

    @Transactional
    public void reset(Long habitId, Long userId){
        HabitDrop habit = getHabitOrThrow(habitId, userId);
        habit.setResetCount(habit.getResetCount() + 1);
        habit.setStartDateTime(null);
    }

    @Transactional
    public void start(Long habitId, Long userId){
        HabitDrop habit = getHabitOrThrow(habitId, userId);
        habit.setActive(true);
        habit.setStartDateTime(LocalDateTime.now());
    }

    private HabitDrop getHabitOrThrow(Long taskId, Long userId) {
        return habitDropRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));
    }
}
