package com.hms.entity.evaluation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "customer_visit")
public class CustomerVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "house_no", nullable = false, unique = true)
    private String houseNo;

    @Column(name = "address_line_1", nullable = false,length = 500)
    private String addressLine1;

    @Column(name = "address_line_2", nullable = false,length = 500)
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Column(name = "date_of_visit",nullable = false,length = 1000)
    private LocalDate dateOfVisit;

    @Column(name = "time_of_visit", nullable = false,length = 1000)
    private LocalTime timeOfVisit;

}