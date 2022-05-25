package io.jpabuddy.spring.demo.jpademo.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class TaskDto implements Serializable {
    private final Long id;
    private final String name;
    private final Instant startDate;
    private final Instant endDate;

    public TaskDto(Long id, String name, Instant startDate, Instant endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto entity = (TaskDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.endDate, entity.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "startDate = " + startDate + ", " +
                "endDate = " + endDate + ")";
    }
}
