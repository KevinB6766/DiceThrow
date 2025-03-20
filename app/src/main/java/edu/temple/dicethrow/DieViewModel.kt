package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DieViewModel: ViewModel()  {
    private val dieSides = 6
    private val currentROLL: MutableLiveData<Int> by lazy {
        MutableLiveData()
    }
    fun getCurrentROLL(): LiveData<Int> {
        return currentROLL
    }
    fun setCurrentROLL(roll: Int) {
        currentROLL.value = roll
    }
    fun rollDie() {

        setCurrentROLL(Random.nextInt(1, dieSides + 1))
    }

}