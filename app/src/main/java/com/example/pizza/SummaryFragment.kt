package com.example.pizza

import android.content.Intent
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
import com.example.pizza.databinding.FragmentSummaryBinding
import com.example.pizza.model.OrderViewModel


class SummaryFragment : Fragment() {

    private  val sharedViewModel: OrderViewModel by activityViewModels ()

    private var binding: FragmentSummaryBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel= sharedViewModel
            sendButton.setOnClickListener { sendOrder() }
            cancelButton.setOnClickListener{ cancelOrder()}
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun sendOrder() {
       val orderSummary = getString(
           R.string.order_details,
           sharedViewModel.slice.value.toString(),
           sharedViewModel.recipe.value.toString(),
           sharedViewModel.date.value.toString(),
           sharedViewModel.price.value.toString()
       )
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_pizza_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        if (activity?.packageManager?.resolveActivity(intent,0) != null) {
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

}