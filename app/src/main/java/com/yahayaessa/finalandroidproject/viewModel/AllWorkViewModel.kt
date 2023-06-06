package com.yahayaessa.finalandroidproject.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahayaessa.finalandroidproject.apiService.ApiService
import com.yahayaessa.finalandroidproject.model.AllWorkData
import kotlinx.coroutines.launch

class AllWorkViewModel : ViewModel() {

    var listAllWorkLiveData: List<AllWorkData> by mutableStateOf(listOf())


    fun getAllWork() {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getAllWorkLis = apiService.getAllWorkResponse()

            listAllWorkLiveData = getAllWorkLis.data


        }
    }

}