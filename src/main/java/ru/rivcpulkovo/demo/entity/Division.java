package ru.rivcpulkovo.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue(generator="division_seq")
    @SequenceGenerator(name="division_seq",sequenceName="division_seq", allocationSize=1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Division parentDivision;
    @Column(name = "dt_from")
    @JsonFormat(timezone = "GMT+03:00")
    private Date dateFrom;
    @Column(name = "dt_till")
    @JsonFormat(timezone = "GMT+03:00")
    private Date dateTo;
    @Column(name = "is_system")
    private Boolean isSystem;
    @Column(name = "creation_date")
    @JsonFormat(timezone = "GMT+03:00")
    private Date creationDate;
    @Column(name = "correction_date")
    @JsonFormat(timezone = "GMT+03:00")
    private Date correctionDate;

}
