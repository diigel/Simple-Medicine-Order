package com.dani.simplemedicineorder.viewmodel

import kotlinx.coroutines.Job

interface MedicineOrderContract {
    fun medicineOrderList() : Job
    fun medicineOrderDetail(id :Int) : Job
}