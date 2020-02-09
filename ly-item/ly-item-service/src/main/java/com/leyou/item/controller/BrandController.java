package com.leyou.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;

@RestController
@RequestMapping("brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("page")
	public ResponseEntity<PageResult<Brand>> queryBrandByPage(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "5") Integer rows,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "desc", defaultValue = "false") Boolean desc,
			@RequestParam(value = "key", required = false) String key) {
		PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);
		if (result == null || result.getItems().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(result);
	}

	/**
	 * 新增品牌
	 * @param brand
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> saveBrand(Brand brand,
			@RequestParam(value = "cids", defaultValue = "76") List<Long> cids) {
		this.brandService.saveBrand(brand, cids);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
