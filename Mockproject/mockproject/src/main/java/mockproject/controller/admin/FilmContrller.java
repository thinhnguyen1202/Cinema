package mockproject.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import mockproject.model.CategoryModel;
import mockproject.model.FilmModel;
import mockproject.service.IShowtimeService;
import mockproject.service.impl.CategoryFilmService;
import mockproject.service.impl.CategoryService;
import mockproject.service.impl.FilmService;
import mockproject.utils.DateFormater;
import mockproject.utils.DateTimeFormat;

@Controller(value = "FilmControllerAdmin")
public class FilmContrller {

	@Autowired
	FilmService filmService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CategoryFilmService categoryFilmService;
	@Autowired
	IShowtimeService showtimeService;

	// Phương thức này được gọi mỗi lần có Submit.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == FilmModel.class) {

			// Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	// Listing film
	@RequestMapping(value = "/admin/film", method = RequestMethod.GET)
	public String listFilm(Model model) {
		List<FilmModel> listFilm = filmService.listAllFilm();
		for (int i = 0; i < listFilm.size(); i++) {
			int film_id = listFilm.get(i).getId();
			List<CategoryModel> listCatgory = categoryService.listAllCategoryByFilmid(film_id);
			listFilm.get(i).setListCategory(listCatgory);
		}
		model.addAttribute("listFilm", listFilm);
		return "/admin/film/filmlist";
	}

	// Get Film create form
	@RequestMapping(value = "/admin/film/create", method = RequestMethod.GET)
	public String createFilm(Model model) {

		System.out.println("Vào đây");
		FilmModel filmModel = new FilmModel();
		List<String> listCategoryName = categoryService.listALlCategoryName();
		model.addAttribute("listCategoryName", listCategoryName);
		model.addAttribute("uploadFilmwithImage", filmModel);

		System.out.println("OK chuyen di ");

		// chuyển tới trang nhập film
		return "/admin/film/filmcreate";
	}

	@RequestMapping(value = "/admin/film/create", method = RequestMethod.POST)
	public String uploadFilmHandlerPost(HttpServletRequest request, Model model,
			@ModelAttribute("uploadFilmwithImage") FilmModel filmModel, HttpSession session) {

		// List các thông tin về danh mục
		List<String> categoryName = filmModel.getListCategoryName();
		// set lại dạnh mục cho nó , trả về được index của danh mục
		List<Integer> listCategoryId = new ArrayList<>();
		for (int i = 0; i < categoryName.size(); i++) {
			String categoryFilmName = categoryName.get(i);
			listCategoryId.add(categoryService.findCategoryIdByName(categoryFilmName));
		}
		// co film id và list index

		// thêm thể hiện vào bảng Category_film
		for (int i = 0; i < listCategoryId.size(); i++) {
			categoryFilmService.insertOneRow(filmService.getTheLastID(), listCategoryId.get(i));
		}

		return this.doUpload(request, model, filmModel);
	}

	private String doUpload(HttpServletRequest request, Model model, FilmModel filmModel) {

		CommonsMultipartFile fileInForm = filmModel.getFileDatas();
		// This for file name only,
		// serves for server to handler
		String fileName = fileInForm.getOriginalFilename();

		// Kiểm tra fileName
		System.out.println("fileName = " + fileName);
		String filePath = null;
		if (!"".equals(fileName)) {
			String dirFile = request.getServletContext().getRealPath("upload");
			System.out.println("Store Directory: " + dirFile);
			File fileDir = new File(dirFile);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			try {

				File serverFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);

				// Ghi giữa liệu
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileInForm.getBytes());
				stream.close();
				filmModel.setImagepath(fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		String showtimeTakenDate = filmModel.getShowDateString();
		Date created_at = DateFormater.toSqlDate(showtimeTakenDate);

		
		filmModel.setCreated_at(created_at);
		filmModel.setUpdate_at(DateTimeFormat.getNow());
		filmService.saveFilm(filmModel);

		return "redirect:/admin/film";
	}

	@RequestMapping(value = "/admin/film/deletefilm/{id}", method = RequestMethod.GET)
	public String deleteFilm(@PathVariable int id) {
		filmService.deleteFilm(id);
		filmService.deleteFilmOnCategoryFilmTable(id);
		categoryFilmService.deleteByFilmID(id);
		//showtimeService.deleteshowtimebyfilmid(id);
		return "redirect:/admin/film";
	}

	@RequestMapping(value = "/admin/film/editfilm/{id}", method = RequestMethod.GET)
	public String editFilm(@PathVariable String id, Model model) {
		FilmModel filmModel = filmService.getFilmById(Integer.valueOf(id));
		List<String> listCategoryName = categoryService.listALlCategoryName();
		List<CategoryModel> listCategoryWithFilm = categoryService.listAllCategoryByFilmid(Integer.valueOf(id));
		List<String> listFilmCategoryName = new ArrayList<>();
		for (int i = 0; i < listCategoryWithFilm.size(); i++) {
			String categoryName = listCategoryWithFilm.get(i).getName();
			listFilmCategoryName.add(categoryName);
		}
		filmModel.setListCategoryName(listFilmCategoryName);

		model.addAttribute("CategoryName", listCategoryName);
		model.addAttribute("editFilmWithImage", filmModel);
		model.addAttribute("filmInfo", filmModel);
		return "/admin/film/filmedit";
	}

	@RequestMapping(value = "/admin/film/editfilm/{id}", method = RequestMethod.POST)
	public String editFilmHanlderPost(HttpServletRequest request, Model model,
			@ModelAttribute("editFilmWithImage") FilmModel filmModel, HttpSession session) {

		FilmModel film = filmModel;
		FilmModel filmInDB = filmService.getFilmById(film.getId());
		
		// update with that list
		updateNewCategory(film);

		// xu ly anh
		// image: null -> giu nguy

		// image: not null -> update moi
		film = editImageUpload(filmModel, request);
		film.setCreated_at(filmInDB.getCreated_at());
		film.setUpdate_at(DateTimeFormat.getNow());
		filmService.updateFilm(film);

		return "redirect:/admin/film/editfilm/" + film.getId();
	}

	// image: null -> giu nguy

	// image: not null -> update moi

	public FilmModel editImageUpload(FilmModel filmModel, HttpServletRequest request) {
		FilmModel film = filmModel;

		CommonsMultipartFile fileDatas = film.getFileDatas();

		// check if file size equals to 0
		if (fileDatas.getSize() == 0) {
			// not update
			FilmModel filmInDatabse = filmService.getFilmById(film.getId());
			String filePath = filmInDatabse.getImagepath();
			film.setImagepath(filePath);
		} else {
			// do update
			CommonsMultipartFile fileInForm = film.getFileDatas();
			String fileName = fileInForm.getOriginalFilename();
			if (!"".equals(fileName)) {
				String dirFile = request.getServletContext().getRealPath("upload");
				System.out.println("Store Directory: " + dirFile);
				File fileDir = new File(dirFile);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				try {

					File serverFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);

					// Ghi giữa liệu
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileInForm.getBytes());
					stream.close();
					film.setImagepath(fileName);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

		return film;
	}

	public void updateNewCategory(FilmModel filmModel) {
		FilmModel film = filmModel;

		// update categofy_film
		// xoa het the hien theo film_id
		categoryFilmService.deleteByFilmID(film.getId());

		// upadte theo list moi
		List<String> listCategoryNew = film.getListCategoryName();
		// lay index cua tung film
		List<String> allCategory = categoryService.listALlCategoryName();

		List<Integer> listIndexOfCategoryOfFilm = new ArrayList<>();
		//
		for (int i = 0; i < listCategoryNew.size(); i++) {
			int indexOfCategory = categoryService.findCategoryIdByName(listCategoryNew.get(i));
			listIndexOfCategoryOfFilm.add(indexOfCategory);
		}

		// them moi the hien vao bang category_film
		for (int i = 0; i < listIndexOfCategoryOfFilm.size(); i++) {
			categoryFilmService.insertOneRow(film.getId(), listIndexOfCategoryOfFilm.get(i));
		}
	}

}
