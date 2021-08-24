package com.dani.simplemedicineorder.ui.activity

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.simplemedicineorder.databinding.ActivityMedicineOrderBinding
import com.dani.simplemedicineorder.ui.adapter.MedicineOrderAdapter
import com.dani.simplemedicineorder.utils.loaderDialog
import com.dani.simplemedicineorder.utils.onSuccess
import com.dani.simplemedicineorder.utils.state.bindToAlertDialog
import com.dani.simplemedicineorder.viewmodel.MedicineOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineOrderActivity : AppCompatActivity() {

    private val binding: ActivityMedicineOrderBinding by viewBinding()
    private val viewModel: MedicineOrderViewModel by viewModels()

    private val medicineOrderAdapter by lazy {
        MedicineOrderAdapter()
    }

    private val loader by lazy {
        loaderDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onView()
    }

    private fun onView() = binding.run {
        rvMedicineOrder.apply {
            layoutManager = GridLayoutManager(this@MedicineOrderActivity,1)
            adapter = medicineOrderAdapter
        }

        viewModel.getMedicineOrderList()
        viewModel.medicineOrderList.observe(this@MedicineOrderActivity, { result ->
            result.bindToAlertDialog(loader)
            result.onSuccess { data ->
                medicineOrderAdapter.addList(data)
            }
        })

        medicineOrderAdapter.onClick {
            startActivity(
                Intent(this@MedicineOrderActivity, MedicineOrderDetailActivity::class.java)
                    .putExtra("id", it.id)
            )
        }
    }
}