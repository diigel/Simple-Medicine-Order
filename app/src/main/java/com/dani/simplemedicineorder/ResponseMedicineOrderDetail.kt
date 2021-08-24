package com.dani.simplemedicineorder


import com.google.gson.annotations.SerializedName

data class ResponseMedicineOrderDetail(
    @SerializedName("address")
    var address: Address?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("patient")
    var patient: Patient?,
    @SerializedName("prescription_image")
    var prescriptionImage: String?,
    @SerializedName("status")
    var status: String?
) {
    data class Address(
        @SerializedName("district")
        var district: String?,
        @SerializedName("first_line")
        var firstLine: String?,
        @SerializedName("id")
        var id: String?,
        @SerializedName("latitude")
        var latitude: Double?,
        @SerializedName("longitude")
        var longitude: Double?,
        @SerializedName("note")
        var note: String?,
        @SerializedName("postal_code")
        var postalCode: String?,
        @SerializedName("province")
        var province: String?,
        @SerializedName("second_line")
        var secondLine: String?,
        @SerializedName("sub_district")
        var subDistrict: String?
    )

    data class Patient(
        @SerializedName("full_name")
        var fullName: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("phone_number")
        var phoneNumber: String?
    )
}