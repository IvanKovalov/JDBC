package org.example;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Entity
public class User {
    private int id;
    private String name;
    private int age;

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }


    @NotBlank(message = "EntityUser's Name must ne mot empty")
    @Pattern(regexp = "[a-z-A-Z]*", message = "EntityUser's First name has invalid characters")
    @Length(min = 1,max = 20,message = "Invalid EntityUser's Name, too many characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int Age){

    }

    @NotNull(message = "EntityUser's Age must be not null")
    @Min(value = 0, message = "EntityUser's Age must be > 0")
    @Max(value = 120, message = "EntityUser's Age must be less than 120")
    @Positive(message = "EntityUser's Age must be positive number")
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString() {
        return "EntityUser:\n" +
                "id: " + id +
                "\n Name: " + name + "\n" +
                "\nAge: " + age + "\n";
    }
}
