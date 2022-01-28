package com.example.demo.vo;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Advance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private float amount;
    private LocalDate date;
    private String name;
    private String description;
    private BillStates state;

    @ManyToOne
    @JoinTable(
            name = "advance_for_mission",
            joinColumns = @JoinColumn(name = "advance_id"),
            inverseJoinColumns = @JoinColumn(name = "mission_id"))
    private Mission mission;


}
