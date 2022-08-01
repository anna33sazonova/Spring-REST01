package com.library.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    
    @Autowired
   private BookRepository bookRepository;

   @GetMapping("/book")
   public String getBook(Model model,
   @RequestParam(required = false) Integer id) {
    Book book = new Book();
    if(id != null) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            book = optionalBook.get();
        }
    }
    model.addAttribute("book", book);
    return "book";
   }

   @GetMapping("/books")
   public String getAll(Model model) {
    model.addAttribute("books", bookRepository.findAll());
     return "books";
   }

   @PostMapping("/book") 
   public String postBook(@ModelAttribute Book book) {
    bookRepository.save(book);
    return "redirect:/books";
   }

   @PutMapping("/book")
   public String updateBook(@ModelAttribute Book book) {
    bookRepository.save(book);
    return "redirect:/books";
   }

   @GetMapping("/book/delete")
   public String deleteBook(@RequestParam Integer id) {
    bookRepository.deleteById(id);
    return "redirect:/books";
   }

   @PostMapping("/book/search")
   public List<Book> search (@RequestBody Map<String, String> body) {
    String searchTerm = body.get("text");
    return bookRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
   }

}
