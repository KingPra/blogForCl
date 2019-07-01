package com.kpr.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BlogController {

	@Autowired
	private BlogRepository blogRepo;

	@GetMapping("/")
	public String getIndexPage(Model model, Blog blog) {
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
	}

	@GetMapping("/new")
	public String getNewPage() {
		return "new";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable Long id, Model model, Blog blog) {
		Blog post = blogRepo.findById(id).orElse(null);
		model.addAttribute("blog", post);
		return "edit";
	}

	@PutMapping("/edit/{id}")
	public String editById(@PathVariable Long id, Model model, Blog blog) {
		Blog post = blogRepo.findById(id).orElse(null);
		post.setAuthor(blog.getAuthor());
		post.setContent(blog.getContent());
		post.setId(blog.getId());
		blogRepo.save(post);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
	}

	@PostMapping("/new")
	public String postNewBlog(Model model, Blog blog) {
		blogRepo.save(blog);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable Long id, Model model) {
		blogRepo.deleteById(id);
		model.addAttribute("blogs", blogRepo.findAll());
		return "index";

	}

}
