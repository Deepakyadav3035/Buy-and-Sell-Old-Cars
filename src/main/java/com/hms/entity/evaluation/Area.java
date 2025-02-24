package com.hms.entity.evaluation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

}