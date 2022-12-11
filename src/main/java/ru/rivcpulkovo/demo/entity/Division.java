package ru.rivcpulkovo.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "divisions")
@NoArgsConstructor
@Data
public class Division {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Division parentDivision;
    @Column(name = "dt_from")
    private Date dateFrom;
    @Column(name = "dt_till")
    private Date dateTo;
    @Column(name = "is_system")
    private Boolean isSystem;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "correction_date")
    private Date correctionDate;

}
