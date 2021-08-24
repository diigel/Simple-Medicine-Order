package com.dani.simplemedicineorder.viewmodel

import kotlinx.coroutines.Job

interface MedicineOrderContract {
    fun getMedicineOrderList() : Job
    fun getMedicineOrderDetail(id :Int) : Job
}