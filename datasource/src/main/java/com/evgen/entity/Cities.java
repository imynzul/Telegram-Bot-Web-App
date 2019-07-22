package com.evgen.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cities", schema = "cityinfo")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "city", unique = true, length = 80, nullable = false)
    private String city;
    @Column(name = "info", nullable = false)
    private String info;

    public Cities() {
    }

    public Cities(long id){
        this(id, null, null);
    }

    public Cities(String city, String info){
        this(0L, city, info);
    }

    public Cities(long id, String city, String info) {
        this.id = id;
        this.city = city;
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cities cities = (Cities) o;
        return id == cities.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cities{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
