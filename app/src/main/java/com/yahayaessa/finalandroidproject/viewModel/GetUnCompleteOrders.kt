package com.yahayaessa.finalandroidproject.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yahayaessa.finalandroidproject.apiService.ApiService
import com.yahayaessa.finalandroidproject.model.OrderRequestData
import com.yahayaessa.finalandroidproject.model.PreferenceManager
import kotlinx.coroutines.launch

class GetUnCompleteOrders : ViewModel() {

    var listUnCompleteOrderLiveData: List<OrderRequestData> by mutableStateOf(listOf())


    fun getUnCompleteOrders(token:String) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getUnComplete = apiService.getUnCompletedOrder(token)

            listUnCompleteOrderLiveData = getUnComplete.data


        }


    }
}