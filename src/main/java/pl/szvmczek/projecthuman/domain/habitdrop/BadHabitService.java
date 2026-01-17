package pl.szvmczek.projecthuman.domain.habitdrop;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitCreateDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitViewDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitUpdateDto;
import pl.szvmczek.projecthuman.domain.user.User;
import pl.szvmczek.projecthuman.domain.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BadHabitService {
    private final BadHabitRepository badHabitRepository;
    private final UserService userService;

    public BadHabitService(BadHabitRepository badHabitRepository, UserService userService) {
        this.badHabitRepository = badHabitRepository;
        this.userService = userService;
    }

    public void create(BadHabitCreateDto dto, Long userId){
        User user = userService.getReferenceById(userId);
        BadHabit habit = BadHabitDtoMapper.map(dto);
        habit.setUser(user);
        badHabitRepository.save(habit);
    }

    public List<BadHabitViewDto> getAll(Long userId){
        return badHabitRepository.findAllByUserId(userId)
                .stream()
                .map(BadHabitDtoMapper::map)
                .toList();
    }

    @Transactional
    public void update(BadHabitUpdateDto dto, Long userId){
        BadHabit originalHabit = getHabitOrThrow(dto.getId(), userId);
        originalHabit.setTitle(dto.getTitle());
        originalHabit.setDescription(dto.getDescription());
    }

    public void delete(Long habitId, Long userId){
        BadHabit habit = getHabitOrThrow(habitId, userId);
        badHabitRepository.delete(habit);
    }

    @Transactional
    public void reset(Long habitId, Long userId){
        BadHabit habit = getHabitOrThrow(habitId, userId);
        habit.setResetCount(habit.getResetCount() + 1);
        habit.setStartDateTime(null);
        habit.setActive(false);
    }

    @Transactional
    public void start(Long habitId, Long userId){
        BadHabit habit = getHabitOrThrow(habitId, userId);
        if(habit.isActive()) return;
        habit.setActive(true);
        habit.setStartDateTime(LocalDateTime.now());
    }

    public BadHabitUpdateDto getHabitForEdit(Long habitId, Long userId) {
        BadHabit habit = getHabitOrThrow(habitId, userId);
        return BadHabitDtoMapper.mapToEdit(habit);
    }

    private BadHabit getHabitOrThrow(Long habitId, Long userId) {
        return badHabitRepository.findByIdAndUserId(habitId, userId)
                .orElseThrow(() -> new EntityNotFoundException("HabitDrop not found!"));
    }
}
