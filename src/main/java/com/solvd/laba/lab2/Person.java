package com.solvd.laba.lab2;

import com.solvd.laba.lab2.interfaces.PersonInfo;

import java.util.Objects;

public abstract class Person implements PersonInfo {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public final int getAge() {
        return this.age;
    }

    @Override
    public final void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person otherPerson = (Person) obj;
        return age == otherPerson.age && Objects.equals(name, otherPerson.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
