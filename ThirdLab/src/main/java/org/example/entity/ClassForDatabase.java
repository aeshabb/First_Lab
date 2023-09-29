package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassForDatabase implements Serializable {
    private List<Enrollee> enrollees;
    private List<Direction> directions;


    public void setEnrolleeList(List<Enrollee> enrollees) {
        this.enrollees = enrollees;
    }

    public void setDirectionList(List<Direction> directions) {
        this.directions = directions;
    }


}
