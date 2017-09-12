package com.digdes.school.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ...
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
@SuppressWarnings("UnusedDeclaration")
@Entity
@Data               //lombok maigic annotations. Use it carefully
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cityId;

    private Double longitude;

    private Double latitude;

    private String name;
}
