package com.dani.simplemedicineorder.data.entity


import com.google.gson.annotations.SerializedName

data class ResponseMedicineOrderList(
    @SerializedName("currency")
    var currency: String? = "",
    @SerializedName("full_name")
    var fullName: String? = "",
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("total_price")
    var totalPrice: Int? = 0,
    @SerializedName("updated_at")
    var updatedAt: String? = ""
)