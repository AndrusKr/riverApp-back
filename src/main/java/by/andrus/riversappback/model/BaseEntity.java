package by.andrus.riversappback.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class BaseEntity extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    // !!! Reflective operations have slower performance than their non-reflective counterparts,
    // !!! and should be avoided in sections of code which are called frequently in performance-sensitive applications.
    public <T> void addMissing(T remoteObject) throws IllegalAccessException {
        List<Field> allFields = new ArrayList<>();
        for (Class<?> c = this.getClass(); c != null; c = c.getSuperclass()) {
            allFields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        for (Field field : allFields) {
            field.setAccessible(true);
            Object localValue = field.get(this);
            Object remoteValue = field.get(remoteObject);
            // WARNING !!! DOES NOT CLONE THE FIELDS FROM remoteObject but simply copies the link to the same objects
            field.set(this, (localValue == null) ? remoteValue : localValue);
        }
    }
}
