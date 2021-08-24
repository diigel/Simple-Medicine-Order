package com.dani.simplemedicineorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineOrderViewModel @Inject constructor(private val repository: MedicineOrderRepositoryImpl)  : ViewModel(), MedicineOrderContract {

    val medicineOrderList = repository.medicineOrderList.asLiveData(viewModelScope.coroutineContext)
    val medicineOrderDetail = repository.medicineOrderDetail.asLiveData(viewModelScope.coroutineContext)

    override fun medicineOrderList(): Job = viewModelScope.launch {
        repository.getMedicineOrderList()
    }

    override fun medicineOrderDetail(id: Int): Job = viewModelScope.launch {
       repository.getMedicineOrderDetail(id)
    }

}