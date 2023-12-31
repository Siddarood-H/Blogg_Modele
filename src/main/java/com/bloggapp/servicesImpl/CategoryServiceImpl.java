package com.bloggapp.servicesImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggapp.entities.Category;
import com.bloggapp.exceptions.ResourceNotFoundException;
import com.bloggapp.payload.CategoryDto;
import com.bloggapp.repositories.CategoryRepo;
import com.bloggapp.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class); 
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
Category cat = this.categoryRepo.findById(categoryId)
.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

cat.setCategoryTitle(categoryDto.getTitle());
cat.setCategoryDescription(categoryDto.getDescription());
Category categoryUpdated=this.categoryRepo.save(cat);

		return this.modelMapper.map(categoryUpdated,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
         this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories= this.categoryRepo.findAll();
		List<CategoryDto>categoryDtos
=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}
	
	

}
