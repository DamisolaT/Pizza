package com.example.pizza

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pizza.databinding.FragmentStartBinding
import com.example.pizza.model.OrderViewModel


class StartFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels ()

   private var binding: FragmentStartBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.startFragment = this


    }

        @RequiresApi(Build.VERSION_CODES.N)
        fun orderPizza(size: Int) {
            sharedViewModel.setSize(size)
            if (sharedViewModel.hasNoRecipeSet()){
                sharedViewModel.setRecipe(getString(R.string.meat))
            }
            findNavController().navigate(R.id.action_startFragment_to_recipeFragment)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }



    }
