package com.revature.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status_name")
    private String name;

    public Status(){
    id = 0;
    name = "";
    }

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id) && Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Status{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}