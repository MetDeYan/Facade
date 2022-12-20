package com.intexsoft.apifacadeservice.entity.roles;

import lombok.*;

import javax.persistence.*;

/**
 * The role permissions entity.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Roles {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    @Lob
    private String permissions;
}