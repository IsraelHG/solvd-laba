package com.solvd.laba.lab1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Includes a class that has simple user info.
class User {
    private final String name;
    private final int age;
    private final String email;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}

public class UserInfo {

    /**
     * Validates whether an email address is valid by checking if it has a valid domain name.
     * A valid email address is defined as having a single "@" character, followed by a domain name
     * with exactly one "." character before the top-level domain.
     *
     * @param email The email address to validate.
     * @return (boolean) True if the email address has a valid domain name, false otherwise.
     */
    private static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * This Java program takes three command-line arguments: <firstName>, <age>, and <email>.
     * Outputs the user's information in a formatted manner.
     * Validates the email address to ensure that it is valid.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------");
        if (args.length < 3) {
            System.err.println("-----> Please provide your name, age, and email address as command-line arguments.");
            System.err.println("-----> java <fileName> <firstName> <(int)age> <email>");
            return;
        }

        // First argument is <firstName>:
        // Capitalizes first letter
        String name = args[0];
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        // Second argument is <age>:
        // Checks if age input is integer.
        // Prints error and ends program if not.
        int age;
        try {
            age = Integer.parseInt(args[1]);
        } catch (NumberFormatException err) {
            System.err.println("-----> Invalid <age>, please enter a valid integer.");
            return;
        }

        // Third argument is <email>
        // email is sent to helper method
        // Checks if email contains "@" and ".com"
        String email = args[2];
        if (!isValidEmail(email)) {
            System.err.println("-----> Invalid <email> address. " +
                    "Please enter a valid email address with a valid domain.");
            return;
        }

        User user = new User(name, age, email);

        System.out.println("-----> Name: " + user.getName());
        System.out.println("-----> Age: " + user.getAge());
        System.out.println("-----> Email: " + user.getEmail());
    }
}
