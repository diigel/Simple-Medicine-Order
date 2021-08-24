package com.dani.simplemedicineorder.repository

import com.dani.simplemedicineorder.utils.state.ResultState
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderDetail
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderList
import kotlinx.coroutines.flow.StateFlow

interface MedicineOrderRepository {
    var medicineOrderList : StateFlow<ResultState<List<ResponseMedicineOrderList>>>
    var medicineOrderDetail : StateFlow<ResultState<ResponseMedicineOrderDetail>>
    suspend fun getMedicineOrderList()
    suspend fun getMedicineOrderDetail(id : Int)
}