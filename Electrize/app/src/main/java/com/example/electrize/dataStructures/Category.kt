package com.example.electrize.dataStructures

class CategoryGroup(categoryGroupId: Int, categoryGroupName: String) {
    var _categoryGroupId: Int = categoryGroupId;
    var _categoryGroupName: String = categoryGroupName;
}

class Category(categoryId: Int, categoryName: String, categoryGroupId: Int, imageId: Int) {
    var _categoryId: Int = categoryId;
    var _categoryName: String = categoryName;
    var _categoryGroup: Int = categoryGroupId;
    var _imageId = imageId;
}