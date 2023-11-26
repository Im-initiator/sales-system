package com.leminhtien.controller.admin;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.leminhtien.entity.CategoryEntity;
import com.leminhtien.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.service.ICategoryService;
import com.leminhtien.service.IProductService;
import com.leminhtien.service.ISizeService;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISizeService sizeService;


	
	@RequestMapping(value = "/admin/product", method = RequestMethod.GET)
	public ModelAndView producttList(@RequestParam("page") int page) {
		ModelAndView mav = new ModelAndView("/admin/product/productList");
		int limit = 3;//có thể thay đổi
		Pageable pageable = new PageRequest(page-1,limit);
		List<ProductDTO> model = productService.findAll(pageable);
		mav.addObject("model",model);
		mav.addObject("totalItem", productService.count());
		mav.addObject("limit", limit);
		mav.addObject("page", page);
		return mav;
	}

	@RequestMapping(value = "/admin/edit/product", method = RequestMethod.POST)
	public ResponseEntity<?> save(@ModelAttribute("model") ProductDTO product){
		System.out.println(product.getCategoryCode());
		System.out.println(product.getCategoryCode().getClass());
		ProductDTO productDTO = productService.save(product);
		if (productDTO != null) {
			return ResponseEntity.ok(productDTO);
		} else {
			return ResponseEntity.badRequest().body("Error: Lưu không thành công".getBytes(StandardCharsets.UTF_8));
		}
	}

	@RequestMapping(value="/admin/edit/product", method = RequestMethod.GET)
	public ModelAndView editProduct(@RequestParam(value="id",required = false) Long id) {	
		ModelAndView mav = new ModelAndView("/admin/product/editProduct");
		ProductDTO product = new ProductDTO();
		if(id != null) {
			 product = productService.findById(id);
			
		}
		mav.addObject("model", product);
		Map<String, String> categories = categoryService.selectAll();
		mav.addObject("categories", categories);
		List<String> sizes = sizeService.findAll();
		mav.addObject("sizes", sizes);
		return mav;
	}
	
	
	

}
