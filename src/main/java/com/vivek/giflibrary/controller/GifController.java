package com.vivek.giflibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vivek.giflibrary.data.CategoryRepository;
import com.vivek.giflibrary.data.GifRepository;

@Controller
public class GifController {

	@Autowired
	private GifRepository gifRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping("/")
	public String listAllGifs(ModelMap modelMap) {
		modelMap.put("gifs", gifRepository.getAllGifs());
		return "home";
	}
	
	@RequestMapping("/gif/{name}")
	public String gifDetails(@PathVariable String name,ModelMap modelMap) {
		modelMap.put("gif", gifRepository.findByName(name));
		return "gif-details";
	}

	@RequestMapping("/categories")
	public String listAllCategories(ModelMap modelMap) {
		modelMap.put("categories", categoryRepository.getAllCategories());
		return "categories";
	}
	
	@RequestMapping("/favorites")
	public String listAllFavorites(ModelMap modelMap) {
		modelMap.put("gifs", gifRepository.getAllFavorites());
		return "favorites";
	}

	@RequestMapping("/category/{id}")
	public String gifDetails(@PathVariable int id,ModelMap modelMap) {
		modelMap.put("category", categoryRepository.findById(id));
		modelMap.put("gifs", gifRepository.findByCategory(id));
		return "category";
	}

	@RequestMapping("/search")
	public String listAllFavorites(@RequestParam String q,ModelMap modelMap) {
		modelMap.put("gifs", gifRepository.findBySearch(q));
		return "search";
	}

}
