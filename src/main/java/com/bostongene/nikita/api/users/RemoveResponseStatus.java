package com.bostongene.nikita.api.users;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class RemoveResponseStatus {
    @Setter(AccessLevel.NONE)
    Long removedId;
}
