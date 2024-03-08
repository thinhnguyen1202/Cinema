package mockproject.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mockproject.model.CategoryModel;
import mockproject.model.FilmModel;
import mockproject.service.ICategoryFilmService;
import mockproject.service.ICategoryService;

@Controller(value = "CategoryControllerForAdmin")
public class CateogoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	ICategoryFilmService categoryFilmService;
	
	// listting Cateogory
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String listCategory(Model model) {
		List<CategoryModel> listCategory = categoryService.listAllCategoryModels();
		
		model.addAttribute("listCategory", listCategory);
		return "/admin/category/categorylist";
	}
	
	//Delete Cateogry
	@RequestMapping(value = "/admin/category/deletecategory/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable int id) {
		
		// delete trong bangr categogry_film
		categoryFilmService.deleteByCategoryId(id);
		// delete trong banrg category
		categoryService.deleteCategoryById(id);
		
		
		return "redirect:/admin/category";
	}
	
	//Create Category : get Form
	@RequestMapping(value = "/admin/category/create", method = RequestMethod.GET )
	public String createCategory(Model model) {
		
		CategoryModel categoryModel = new CategoryModel();
		model.addAttribute("uploadCategory", categoryModel);
		
		return "/admin/category/categorycreate";
	}
	
	@RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
	public String createCategoryPost(Model model, @ModelAttribute("uploadCategory") CategoryModel categoryModel) {
		
		int insertOneCategoryID = categoryService.insertOneCategoy(categoryModel);
	
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "/admin/category/editcategory/{id}", method = RequestMethod.GET)
	public String editCategory(@PathVariable int id, Model model) {
		CategoryModel categoryModel = categoryService.findCategoryById(id);
		model.addAttribute("editCategory", categoryModel);
		return "/admin/category/categoryedit";
	}
	
	@RequestMapping(value = "/admin/category/editcategory/{id}", method = RequestMethod.POST)
	public String editCategoryPost(Model model,
			@ModelAttribute("editCategory") CategoryModel categoryModel) {
		int id = categoryModel.getId();
		String name = categoryModel.getName();
		categoryService.updateCategory(id, name);
		return "redirect:/admin/category";
	}

}
