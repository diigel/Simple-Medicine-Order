package com.dani.simplemedicineorder.data.entity

data class MedicineOrderListDto(
    var id : Int? = 0,
    var status : String? = "",
    var fullName : String? = "",
    var totalPrice : Int? = 0,
    var currency : String? = "",
    var updatedAt : String? = ""
)
