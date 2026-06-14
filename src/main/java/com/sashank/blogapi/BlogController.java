package com.sashank.blogapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogController {

    private static List<String> posts = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createPost(@RequestParam String title, @RequestParam String content) {
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Title cannot be empty");
        }
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Content cannot be empty");
        }
        String post = title + ":" + content;
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created");
    }

    @GetMapping
    public List<String> getAllPosts() {
        return posts;
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable int id) {
        return posts.get(id);
    }

    @PostMapping("/validate")
    public String validateContent(@RequestParam String content) {
        if (content.length() > 5000) {
            return "Too long";
        }
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable int id) {
        posts.remove(id);
        return "Deleted";
    }

@GetMapping("/total")
public String getTotalWordCount() {
    List<String> wordCounts = List.of("100", "200", "300");
    String total = "";
    for (String count : wordCounts) {
        total += count;
    }
    return "Total words: " + total;
}
}