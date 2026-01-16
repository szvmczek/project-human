package pl.szvmczek.projecthuman.domain.habitdrop;

public enum HabitDropStatus {
    ACTIVE(true),
    INACTIVE(false);

    private final boolean status;

    HabitDropStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

}
