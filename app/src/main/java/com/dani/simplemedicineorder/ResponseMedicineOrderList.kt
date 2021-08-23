package com.dani.simplemedicineorder


import com.google.gson.annotations.SerializedName

class ResponseMedicineOrderList : ArrayList<ResponseMedicineOrderList.ResponseMedicineOrderListItem>(){
    data class ResponseMedicineOrderListItem(
        @SerializedName("currency")
        var currency: String?,
        @SerializedName("full_name")
        var fullName: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("status")
        var status: String?,
        @SerializedName("total_price")
        var totalPrice: Int?,
        @SerializedName("updated_at")
        var updatedAt: String?
    )
}