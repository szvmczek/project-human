package pl.szvmczek.projecthuman.domain.habitdrop;

import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropAddDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropDto;
import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropEditDto;

public class HabitDropDtoMapper {

    static HabitDrop map(HabitDropAddDto habitDropDto){
        return new HabitDrop(habitDropDto.getTitle(), habitDropDto.getDescription());
    }

    static HabitDropEditDto mapToEdit(HabitDrop habit){
        return new HabitDropEditDto(habit.getId(), habit.getTitle(), habit.getDescription());
    }

    static HabitDropDto map(HabitDrop habitDrop){
        return new HabitDropDto(
                habitDrop.getId(),
                habitDrop.getTitle(),
                habitDrop.getDescription(),
                habitDrop.isActive() ? HabitDropStatus.ACTIVE : HabitDropStatus.INACTIVE,
                habitDrop.getStartDateTime(),
                habitDrop.getResetCount());
    }
}
