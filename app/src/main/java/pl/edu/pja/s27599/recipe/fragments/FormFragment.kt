package pl.edu.pja.s27599.recipe.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pl.edu.pja.s27599.recipe.R
import pl.edu.pja.s27599.recipe.data.FoodRepository
import pl.edu.pja.s27599.recipe.data.FoodRepositoryInMemory
import pl.edu.pja.s27599.recipe.data.RepositoryLocator
import pl.edu.pja.s27599.recipe.databinding.FragmentFormBinding
import pl.edu.pja.s27599.recipe.model.Food
import pl.edu.pja.s27599.recipe.model.FormType

private const val TYPE_KEY = "type"


class FormFragment : Fragment() {
    private lateinit var type: FormType
    private lateinit var binding: FragmentFormBinding
    private lateinit var foodRepository: FoodRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodRepository = RepositoryLocator.foodRepository
        arguments?.let {
            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable(TYPE_KEY, FormType::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getSerializable(TYPE_KEY) as? FormType
            } ?: FormType.New
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentFormBinding.inflate(layoutInflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.Button) {
            text = when (type) {
                is FormType.Edit -> getString(R.string.save)
                FormType.New -> getString(R.string.add)
            }
            setOnClickListener {
                saveFood()
                findNavController().popBackStack()
            }
        }
    }

    private fun saveFood() {
       val food =  Food(R.drawable.rice,
            binding.fieldName.text.toString(),
            binding.fieldSubtitle.text.toString().let {
                if(it.contains(",")) it.split(",") else listOf(it)
            })
        foodRepository.add(food)
    }

}