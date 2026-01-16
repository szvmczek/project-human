package pl.szvmczek.projecthuman.domain.habitdrop;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropAddDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropEditDto;
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

    public void create(HabitDropAddDto dto, Long userId){
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
    public void update(HabitDropEditDto dto, Long userId){
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
        habit.setActive(false);
    }

    @Transactional
    public void start(Long habitId, Long userId){
        HabitDrop habit = getHabitOrThrow(habitId, userId);
        if(habit.isActive()) return;
        habit.setActive(true);
        habit.setStartDateTime(LocalDateTime.now());
    }

    public HabitDropEditDto getHabitForEdit(Long habitId, Long userId) {
        HabitDrop habit = getHabitOrThrow(habitId, userId);
        return HabitDropDtoMapper.mapToEdit(habit);
    }

    private HabitDrop getHabitOrThrow(Long habitId, Long userId) {
        return habitDropRepository.findByIdAndUserId(habitId, userId)
                .orElseThrow(() -> new EntityNotFoundException("HabitDrop not found!"));
    }
}
