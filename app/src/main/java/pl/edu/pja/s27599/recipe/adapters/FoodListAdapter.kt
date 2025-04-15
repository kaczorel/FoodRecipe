package pl.edu.pja.s27599.recipe.adapters

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.s27599.recipe.R
import pl.edu.pja.s27599.recipe.databinding.ItemFoodBinding
import pl.edu.pja.s27599.recipe.model.Food

class FoodItem(val itemViewBinding: ItemFoodBinding): RecyclerView.ViewHolder(itemViewBinding.root){
    fun onBind(foodItem: Food) = with(itemViewBinding){
        image.setImageResource(foodItem.icon)
        title.text = foodItem.name
        subtitle.text = foodItem.ingredients.joinToString(", ")
    }
}

class FoodListAdapter: RecyclerView.Adapter<FoodItem>() {
     var foodList: List<Food> = emptyList()
         set(value){
             field = value;
             notifyDataSetChanged()
         }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodItem {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(layoutinflater,parent,false)
//            .inflate(R.layout.item_food, parent, false)
        return FoodItem(binding)
    }

    override fun onBindViewHolder(
        holder: FoodItem,
        position: Int
    ) {
        holder.onBind(foodList[position])
    }

    override fun getItemCount(): Int = foodList.size
}