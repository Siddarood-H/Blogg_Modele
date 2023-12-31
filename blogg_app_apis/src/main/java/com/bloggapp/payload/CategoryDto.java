package com.bloggapp.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min=4, message="min size of category title is 4")
	private String title;
	@NotBlank
	@Size(min=10,message="min size of category desc is 10")
	private String description;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
