package com.dani.simplemedicineorder.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.dani.simplemedicineorder.R
import com.dani.simplemedicineorder.databinding.ActivityMedicineOrderDetailBinding
import com.dani.simplemedicineorder.utils.loaderDialog
import com.dani.simplemedicineorder.utils.onSuccess
import com.dani.simplemedicineorder.utils.state.bindToAlertDialog
import com.dani.simplemedicineorder.viewmodel.MedicineOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineOrderDetailActivity : AppCompatActivity() {

    private val binding: ActivityMedicineOrderDetailBinding by viewBinding()
    private val viewModel: MedicineOrderViewModel by viewModels()

    private val id by lazy {
        intent?.getIntExtra("id", 0)
    }

    private val loader by lazy {
        loaderDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onView()
    }

    @SuppressLint("SetTextI18n")
    private fun onView() = binding.run {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        viewModel.getMedicineOrderDetail(id ?: 0)
        viewModel.medicineOrderDetail.observe(this@MedicineOrderDetailActivity, { result ->
            result.bindToAlertDialog(loader)
            result.onSuccess { data ->
                txtStatus.text = data.status
                when (data.status) {
                    "Prescription Rejected" -> txtStatus.setTextColor(Color.RED)
                    "In Review" -> txtStatus.setTextColor(ContextCompat.getColor(this@MedicineOrderDetailActivity, R.color.dark_yellow))
                    "Active" -> txtStatus.setTextColor(Color.BLACK)
                }
                txtDesc.isGone = data.status != "Active"
                txtOrderNumber.text = "#${data.id}"
                txtPatientName.text = data.patient?.fullName
                txtPhone.text = data.patient?.phoneNumber
                txtAddress.text = "${data.address?.firstLine}, ${data.address?.secondLine}, " +
                        "${data.address?.subDistrict}, ${data.address?.district}, " +
                        "${data.address?.province}, ${data.address?.postalCode}"

                Glide.with(this@MedicineOrderDetailActivity)
                    .load(data.prescriptionImage)
                    .into(imgPresciption)
            }
        })
    }

}