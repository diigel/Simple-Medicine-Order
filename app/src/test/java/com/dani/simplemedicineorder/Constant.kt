package com.dani.simplemedicineorder

object Constant {
    val medicineOrder1 = MedicineOrderListDto(
        id = 549,
        status = "Active",
        fullName = "Toni",
        totalPrice = 33500,
        currency = "IDR",
        updatedAt = "2020-01-01 14:23:08"
    )
    val medicineOrder2 = MedicineOrderListDto(
        id = 597,
        status = "Prescription Rejected",
        fullName = "Wandi",
        totalPrice = 0,
        currency = "",
        updatedAt = "2020-02-01 14:32:39"
    )
    val medicineOrder3 = MedicineOrderListDto(
        id = 570,
        status = "In Review",
        fullName = "Hendra",
        totalPrice = 0,
        currency = "",
        updatedAt = "2020-03-15 10:37:03"
    )

    val medicineOrderDetail = ResponseMedicineOrderDetail(
        id = 5,
        prescriptionImage = "https://img.1800contacts.com/image/upload/f_auto,q_auto/v1571429939/web/Written-Regular.png",
        status = "In Review",
        patient = ResponseMedicineOrderDetail.Patient(
            id = 1,
            fullName = "Edward Joe",
            phoneNumber = "+6212345678"
        ),
        address = ResponseMedicineOrderDetail.Address(
            id = "5",
            postalCode = "98767",
            latitude = 101.123123,
            longitude = -6.123123,
            firstLine = "Menara Palma",
            secondLine = "Jl. Hr. Rasuna Said Blok X2 No. 6",
            note = "Graha Irama",
            province = "DKI Jakarta",
            district = "South Jakarta",
            subDistrict = "Setiabudi"
        )
    )
}