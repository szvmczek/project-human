package pl.szvmczek.projecthuman.domain.habitdrop;

import pl.szvmczek.projecthuman.domain.habitdrop.dto.HabitDropDto;

public class HabitDropDtoMapper {
    static HabitDrop map(HabitDropDto habitDropDto){
        return new HabitDrop(habitDropDto.getTitle(), habitDropDto.getDescription());
    }

    static HabitDropDto map(HabitDrop habitDrop){
        return new HabitDropDto(habitDrop.getId(), habitDrop.getTitle(), habitDrop.getDescription());
    }
}
