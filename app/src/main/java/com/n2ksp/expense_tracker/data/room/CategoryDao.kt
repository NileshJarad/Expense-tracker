package com.n2ksp.expense_tracker.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryDBModel>

    @Query("SELECT * FROM category where category_type LIKE  :type")
    fun findByType(type: String): List<CategoryDBModel>

    @Query("SELECT * FROM category where category_id LIKE  :id")
    fun findById(id: Int): List<CategoryDBModel>

    @Query("SELECT COUNT(*) from category")
    fun countCategories(): Int

}