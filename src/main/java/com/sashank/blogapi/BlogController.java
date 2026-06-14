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
    public ResponseEntity<String> createPost(@RequestBody Post request) {
        String title=request.getTitle();
        String content=request.getContent();
        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Title cannot be empty");
        }
        if(title.length()>50){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Title cannot be longer than 50 characters");
        }
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Content cannot be empty");
        }
        if(content.length()>1000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Content cannot be longer than 1000 characters");
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
    public ResponseEntity<String> getPost(@PathVariable int id) {
        if(id<0 || id>=posts.size()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Post not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(posts.get(id));
    }

    @PostMapping("/validate")
    public String validateContent(@RequestParam String content) {
        if (content.length() > 5000) {
            return "Too long";
        }
        return "OK";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        if(id<0 || id> posts.size()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Post not found");
        }
        posts.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted");
    }

@GetMapping("/total")
public String getTotalWordCount() {
    List<Integer> wordCounts = List.of(100, 200, 300);
    int total = 0;
    for (int count : wordCounts) {
        total += count;
    }
    return "Total words: " + total;
}
}