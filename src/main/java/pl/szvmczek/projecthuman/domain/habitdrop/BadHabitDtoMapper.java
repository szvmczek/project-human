package pl.szvmczek.projecthuman.domain.habitdrop;

import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitCreateDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitViewDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.BadHabitUpdateDto;

public class BadHabitDtoMapper {

    static BadHabit map(BadHabitCreateDto habitDropDto){
        return new BadHabit(habitDropDto.getTitle(), habitDropDto.getDescription());
    }

    static BadHabitUpdateDto mapToEdit(BadHabit habit){
        return new BadHabitUpdateDto(habit.getId(), habit.getTitle(), habit.getDescription());
    }

    static BadHabitViewDto map(BadHabit badHabit){
        return new BadHabitViewDto(
                badHabit.getId(),
                badHabit.getTitle(),
                badHabit.getDescription(),
                badHabit.isActive(),
                badHabit.getStartDateTime(),
                badHabit.getResetCount());
    }
}
