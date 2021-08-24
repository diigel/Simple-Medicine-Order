package com.dani.simplemedicineorder.data.entity


import com.google.gson.annotations.SerializedName

data class ResponseMedicineOrderList(
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