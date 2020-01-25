package com.andyshon.jetpackcompose

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val state = MutableLiveData<MainViewState>()
    private var items = arrayListOf<MainContentModel>()

    fun loadData() {
        state.value = MainViewState.Loading
        Handler().postDelayed({
            setDummyData()
            state.value = MainViewState.HasData(items)
        }, 2000)
    }

    private fun setDummyData() {
        items.clear()
        items.addAll(arrayListOf(
            MainContentModel("Washington", R.drawable.washington),
            MainContentModel("Berlin", R.drawable.berlin),
            MainContentModel("Paris", R.drawable.paris),
            MainContentModel("Havana", R.drawable.havana)
//            MainContentModel("Moscow", R.drawable.moscow),
//            MainContentModel("Berlin", R.drawable.berlin),
//            MainContentModel("Amsterdam", R.drawable.amsterdam),
//            MainContentModel("Vienna", R.drawable.vienna),
//            MainContentModel("Minsk", R.drawable.minsk),
        ))
    }

    fun deleteItem(item: MainContentModel) {
        items.remove(item)
        if (items.isNotEmpty()) {
            state.value = MainViewState.HasData(items)
        }
        else {
            state.value = MainViewState.Error("You removed all cards :)")
        }
    }
}