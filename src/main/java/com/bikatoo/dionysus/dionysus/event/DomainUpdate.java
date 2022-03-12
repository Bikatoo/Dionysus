package com.bikatoo.dionysus.dionysus.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
public class DomainUpdate<T> implements Serializable {

    T before;

    T after;

    @JsonIgnore
    public boolean isUpdated() {
        return !Objects.equals(before, after);
    }
}
