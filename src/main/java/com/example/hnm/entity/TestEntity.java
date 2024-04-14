package com.example.hnm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "TestEntity")
@Table(name = "CategoryBig")
public class TestEntity {

    @Id
    @Column(length = 50) // varchar(50)으로 매핑
    private String category_big_code;
    
    @Column(length = 255) // varchar(50)으로 매핑
    private String category_big_name;
}
