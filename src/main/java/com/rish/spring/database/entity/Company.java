package com.rish.spring.database.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;


@NamedQuery(
        name = "Company.findByName",
        query = "select c from  Company c where lower(c.name) = lower(:name2) "
)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();


}
