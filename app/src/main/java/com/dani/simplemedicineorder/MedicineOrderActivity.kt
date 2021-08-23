package com.dani.simplemedicineorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.dani.simplemedicineorder.databinding.ActivityMedicineOrderBinding

class MedicineOrderActivity : AppCompatActivity() {

    private val binding : ActivityMedicineOrderBinding by viewBinding()
    private val medicineOrderAdapter by lazy {
        MedicineOrderAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            rvMedicineOrder.layoutManager = LinearLayoutManager(this@MedicineOrderActivity)
            rvMedicineOrder.adapter = medicineOrderAdapter
        }
    }
}