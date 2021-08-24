package com.dani.simplemedicineorder

import kotlinx.coroutines.flow.StateFlow

interface MedicineOrderRepository {
    var medicineOrderList : StateFlow<ResultState<List<MedicineOrderListDto>>>
    var medicineOrderDetail : StateFlow<ResultState<ResponseMedicineOrderDetail>>
    suspend fun getMedicineOrderList()
    suspend fun getMedicineOrderDetail(id : Int)
}