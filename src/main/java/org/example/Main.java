package org.example;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {

            FileReader reader = new FileReader("books.json");
            Gson gson = new Gson();
            Person[] visitors = gson.fromJson(reader, Person[].class);

            // Задание 1:
            List<Person> visitorList = List.of(visitors);
            System.out.println("Visitors:");
            visitorList.forEach(System.out::println);
            System.out.println("Total visitors: " + visitorList.size());
            System.out.println();

            // Задание 2:
            Set<Book> uniqueBooks = visitorList.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .collect(Collectors.toSet());

            System.out.println("Unique books:");
            uniqueBooks.forEach(System.out::println);
            System.out.println("Total unique books: " + uniqueBooks.size());
            System.out.println();

            // Задание 3:
            List<Book> sortedBooks = uniqueBooks.stream()
                    .sorted(Comparator.comparingInt(Book::getPublishingYear))
                    .toList();

            System.out.println("Books sorted by publishing year:");
            sortedBooks.forEach(System.out::println);
            System.out.println();

            // Задание 4:
            boolean hasBook = visitorList.stream()
                    .flatMap(visitor -> visitor.getFavoriteBooks().stream())
                    .anyMatch(book -> "Jane Austen".equals(book.getAuthor()));

            System.out.println("Is there a book by Jane Austen: " + hasBook);
            System.out.println();

            // Задание 5:
            int maxFavoriteBooks = visitorList.stream()
                    .mapToInt(visitor -> visitor.getFavoriteBooks().size())
                    .max()
                    .orElse(0);

            System.out.println("Maximum number of favorite books per visitor: " + maxFavoriteBooks);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File 'books.json' not found.");
        }
    }
}