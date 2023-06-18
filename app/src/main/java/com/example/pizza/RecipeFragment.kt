package com.example.pizza

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pizza.databinding.FragmentRecipeBinding
import com.example.pizza.model.OrderViewModel

class RecipeFragment : Fragment() {


    private  val shareViewModel: OrderViewModel by activityViewModels ()
    private var binding: FragmentRecipeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val fragmentBinding = FragmentRecipeBinding.inflate(inflater, container,false)
        binding = fragmentBinding
        return  fragmentBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = shareViewModel
            nextButton.setOnClickListener { goToNextScreen()}
            cancelButton.setOnClickListener{cancelOrder()}
        }
    }

    fun goToNextScreen() {
       findNavController().navigate(R.id.action_recipeFragment_to_deliveryFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun cancelOrder() {
        shareViewModel.resetOrder()
        findNavController().navigate(R.id.action_recipeFragment_to_startFragment)

    }


    }
