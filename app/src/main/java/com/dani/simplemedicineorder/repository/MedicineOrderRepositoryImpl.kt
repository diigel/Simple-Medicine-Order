package com.dani.simplemedicineorder.repository

import com.dani.simplemedicineorder.utils.state.ResultState
import com.dani.simplemedicineorder.data.Service
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderDetail
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderList
import com.dani.simplemedicineorder.utils.fetch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MedicineOrderRepositoryImpl @Inject constructor(private val service: Service) :
    MedicineOrderRepository {

    private val _medicineOrderList: MutableStateFlow<ResultState<List<ResponseMedicineOrderList>>> =
        MutableStateFlow(ResultState.Idle())
    private val _medicineOrderDetail: MutableStateFlow<ResultState<ResponseMedicineOrderDetail>> =
        MutableStateFlow(ResultState.Idle())

    override var medicineOrderList: StateFlow<ResultState<List<ResponseMedicineOrderList>>> = _medicineOrderList
    override var medicineOrderDetail: StateFlow<ResultState<ResponseMedicineOrderDetail>> = _medicineOrderDetail

    override suspend fun getMedicineOrderList() {
        fetch {
            service.getMedicineOrderList()
        }.collect {
            _medicineOrderList.value = it
        }
    }

    override suspend fun getMedicineOrderDetail(id : Int) {
        fetch {
            service.getMedicineOrderDetail(id)
        }.collect {
            _medicineOrderDetail.value = it
        }
    }
}