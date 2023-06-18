package com.example.pizza.model

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.service.autofill.Transformation
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import java.util.*

private const val PRICE_PER_SLICE = 500.00

private const val PRICE_FOR_SAME_DAY_DELIVERY = .00

@RequiresApi(Build.VERSION_CODES.N)
class OrderViewModel: ViewModel() {

    private  val _slice  = MutableLiveData<Int>()
    val slice: LiveData<Int>  = _slice

    private  val _recipe = MutableLiveData<String>()
    val recipe : LiveData<String> = _recipe

    val dateOptions = getDeliveryOptions()

    private  val _date = MutableLiveData<String>()
    val date : LiveData<String> = _date

    private val _price = MutableLiveData<Double> ()
    val price : LiveData<Double> = _price



    init {
        resetOrder()
    }

    fun setSize(numberSlices: Int) {
        _slice.value = numberSlices
        updatePrice()
    }
    fun setRecipe(desiredRecipe: String){
        _recipe.value = desiredRecipe
    }

    fun setDate(deliveryDate: String){
        _date.value = deliveryDate
        updatePrice()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun resetOrder(){
        _slice.value = 0
        _recipe.value= ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }


    fun hasNoRecipeSet(): Boolean {
        return _recipe.value.isNullOrEmpty()
    }

    private  fun updatePrice() {
        var calculatedPrice = (slice.value ?: 0) * PRICE_PER_SLICE

        if (dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_SAME_DAY_DELIVERY

        }
        _price.value = calculatedPrice


        }

    private  fun getDeliveryOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }




}




