package com.dani.simplemedicineorder

import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    companion object {
        const val MEDICINE_ORDER_LIST = "medications"
        const val MEDICINE_ORDER_DETAIL = "medications/{id}"

        fun create() : Service {
            return Network.build().create(Service::class.java)
        }
    }

    @GET(MEDICINE_ORDER_LIST)
    suspend fun getMedicineOrderList() : ResponseMedicineOrderList

    @GET(MEDICINE_ORDER_DETAIL)
    suspend fun getMedicineOrderDetail(
        @Path("id") id : Int,
    ) : ResponseMedicineOrderDetail
}