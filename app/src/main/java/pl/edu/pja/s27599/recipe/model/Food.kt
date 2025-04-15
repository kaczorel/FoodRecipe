package pl.edu.pja.s27599.recipe.model

import androidx.annotation.DrawableRes

data class Food(
    @DrawableRes
    val icon: Int,
    val name: String,
    val ingredients: List<String>,
)
