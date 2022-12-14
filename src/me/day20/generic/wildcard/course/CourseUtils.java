package me.day20.generic.wildcard.course;

import me.day20.generic.wildcard.course.register.Animal;
import me.day20.generic.wildcard.course.register.Person;

public interface CourseUtils {
//    static <T extends Person> void registerPersonCourse(Course<T> course) { // Course<Person> .....
//        System.out.println(course + " is registered successfully.\n\n");
//    }
//    static <T extends Animal> void registerAnimalCourse(Course<T> course) { // Course<Animal>, Course<Cat>, Course<Dog>
//        System.out.println(course + " is registered successfully.\n\n");
//    }

    static void registerPersonCourse(Course<? extends Person> course) {
        System.out.println(course + " is registered successfully.\n\n");
    }
    static void registerAnimalCourse(Course<? extends Animal> course) {
        System.out.println(course + " is registered successfully.\n\n");
    }

}
