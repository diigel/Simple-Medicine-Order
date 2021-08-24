package com.dani.simplemedicineorder.utils

import com.dani.simplemedicineorder.utils.state.ResultState
import com.dani.simplemedicineorder.data.entity.MedicineOrderListDto
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderList

object Mapper {

    fun mapMedicineOrderListResponseToDto(medicineOrderList: List<ResponseMedicineOrderList>): List<MedicineOrderListDto> {
        return medicineOrderList.map { mapper ->
            MedicineOrderListDto(
                id = mapper.id ?: 0,
                status = mapper.status ?: "",
                fullName = mapper.fullName ?: "",
                totalPrice = mapper.totalPrice ?: 0,
                currency = mapper.currency ?: "",
                updatedAt = mapper.updatedAt ?: ""
            )
        }
    }

    inline fun <T: Any, U: Any> mapResult(resultState: ResultState<out T>, mapper: T.() -> U): ResultState<U> {
        return when (resultState) {
            is ResultState.Success -> {
                val data = resultState.data
                val mapData = mapper.invoke(data)
                ResultState.Success(mapData)
            }
            is ResultState.Idle -> ResultState.Idle()
            is ResultState.Error -> ResultState.Error(resultState.throwable)
            is ResultState.Loading -> ResultState.Loading()
        }
    }
}