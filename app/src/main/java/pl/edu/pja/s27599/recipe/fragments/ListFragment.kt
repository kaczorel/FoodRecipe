package pl.edu.pja.s27599.recipe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.s27599.recipe.R
import pl.edu.pja.s27599.recipe.adapters.FoodListAdapter
import pl.edu.pja.s27599.recipe.data.FoodRepository
import pl.edu.pja.s27599.recipe.data.FoodRepositoryInMemory
import pl.edu.pja.s27599.recipe.data.RepositoryLocator
import pl.edu.pja.s27599.recipe.databinding.ActivityMainBinding
import pl.edu.pja.s27599.recipe.databinding.FragmentListBinding
import pl.edu.pja.s27599.recipe.model.FormType


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    lateinit var foodRepository: FoodRepository
    lateinit var foodListAdapter: FoodListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodRepository = RepositoryLocator.foodRepository


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(layoutInflater,container,false)
            .also{
                binding = it
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        foodListAdapter = FoodListAdapter()
        binding.FoodList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =  foodListAdapter
        }

        foodListAdapter.foodList = foodRepository.getFoodList()

        findNavController().addOnDestinationChangedListener({controller, destination, arguments ->
            if (destination.id == R.id.listFragment){
                foodListAdapter.foodList = foodRepository.getFoodList()
            }
        })

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_formFragment,
                bundleOf("type" to FormType.New))
        }
    }

}