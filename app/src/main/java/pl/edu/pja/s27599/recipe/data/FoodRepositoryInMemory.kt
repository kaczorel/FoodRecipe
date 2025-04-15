package pl.edu.pja.s27599.recipe.data

import pl.edu.pja.s27599.recipe.R
import pl.edu.pja.s27599.recipe.model.Food


object FoodRepositoryInMemory: FoodRepository {
    private val foodList = mutableListOf<Food>(
        Food(R.drawable.pizza,"Pizza",listOf("Sos,Pomidorowy","ser mozarella")),
        Food(R.drawable.pumpkin,"Zupa Dyniowa",listOf("krem z dyni","Grzanki")),
    )
    override fun getFoodList(): List<Food> {
        return foodList
    }

    override fun add(food: Food) {
        foodList.add(food)
    }
}