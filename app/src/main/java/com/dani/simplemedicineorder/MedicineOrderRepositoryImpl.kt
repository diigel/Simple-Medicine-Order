package com.dani.simplemedicineorder

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MedicineOrderRepositoryImpl @Inject constructor(private val service: Service) : MedicineOrderRepository {

    private val _medicineOrderList: MutableStateFlow<ResultState<List<MedicineOrderListDto>>> =
        MutableStateFlow(ResultState.Idle())
    private val _medicineOrderDetail: MutableStateFlow<ResultState<ResponseMedicineOrderDetail>> =
        MutableStateFlow(ResultState.Idle())

    override var medicineOrderList: StateFlow<ResultState<List<MedicineOrderListDto>>> = _medicineOrderList
    override var medicineOrderDetail: StateFlow<ResultState<ResponseMedicineOrderDetail>> = _medicineOrderDetail

    override suspend fun getMedicineOrderList() {
        fetch {
            service.getMedicineOrderList()
        }.map {
            Mapper.mapResult(it) {
                Mapper.mapMedicineOrderListResponseToDto(this)
            }
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