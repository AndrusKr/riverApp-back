package by.andrus.riversappback.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreatedDate
    private Date created = new Date();

    @LastModifiedDate
    private Date updated = new Date();

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
}
