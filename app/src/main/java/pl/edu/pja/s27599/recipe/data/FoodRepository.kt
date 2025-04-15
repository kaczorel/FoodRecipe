package pl.edu.pja.s27599.recipe.data

import pl.edu.pja.s27599.recipe.model.Food

interface FoodRepository {
    fun getFoodList(): List<Food>
    fun add(food:Food)
}