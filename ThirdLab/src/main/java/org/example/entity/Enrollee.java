package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollee {
    private int id;
    private Subject[] subjects;
    private Division[] division;
    private boolean privileges;
    private boolean target;
    private Division originalsToDivision;

}
