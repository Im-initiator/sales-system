package com.leminhtien.controller.admin.saler;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.service.ICategoryGenderService;
import com.leminhtien.service.ICategoryService;
import com.leminhtien.service.IProductService;
import com.leminhtien.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@PreAuthorize("hasAnyRole('SALER')")
@Controller(value = "salerProduct")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISizeService sizeService;

	@Autowired
	private ICategoryGenderService categoryGenderService;


	//@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/saler/product", method = RequestMethod.GET)
	public ModelAndView producttList(@RequestParam("page") int page, @RequestParam(value = "name",required = false) String name) {
		ModelAndView mav = new ModelAndView("/admin/product/productList");
		int limit = 10;//có thể thay đổi
		Pageable pageable = new PageRequest(page-1,limit);
		List<ProductDTO> model = new ArrayList<>();
		long count =0;
		if(name == null){
			 model = productService.findAllByShopId(pageable);
			 count = productService.count(true);
		}else{
			model = productService.searchByName(name,pageable);
			count = productService.countByName(name);
		}
		mav.addObject("model",model);
		mav.addObject("totalItem", count);
		mav.addObject("limit", limit);
		mav.addObject("page", page);
		mav.addObject("api","/api/saler/product");
		mav.addObject("link","/saler/product");
		return mav;
	}

	@RequestMapping(value = "/saler/edit/product", method = RequestMethod.POST)
	public ResponseEntity<?> save(@ModelAttribute("model") ProductDTO product){
		ProductDTO productDTO = productService.save(product);
		if (productDTO != null) {
			return ResponseEntity.ok(productDTO);
		} else {
			return ResponseEntity.badRequest().body("Error: Lưu không thành công".getBytes(StandardCharsets.UTF_8));
		}
	}

	@RequestMapping(value="/saler/edit/product", method = RequestMethod.GET)
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
		mav.addObject("genders",categoryGenderService.findAll());
		return mav;
	}
	
	
	

}
