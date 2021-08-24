package com.dani.simplemedicineorder.ui.viewholder

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dani.simplemedicineorder.R
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderList
import com.dani.simplemedicineorder.databinding.ItemListMedicineBinding
import com.dani.simplemedicineorder.utils.apiToDateTime
import com.dani.simplemedicineorder.utils.currencyFormatWithRp

class MedicineOrderViewHolder(private val itemListMedicineBinding: ItemListMedicineBinding) :
    RecyclerView.ViewHolder(itemListMedicineBinding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(
        data: ResponseMedicineOrderList,
        click: ((data: ResponseMedicineOrderList) -> Unit)? = null
    ) = itemListMedicineBinding.run {
        when (data.status) {
            "Prescription Rejected" -> txtStatus.setTextColor(Color.RED)
            "In Review" -> txtStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.dark_yellow))
            "Active" -> txtStatus.setTextColor(Color.BLACK)
        }
        txtStatus.text = data.status
        txtName.text = data.fullName
        txtDate.text = data.updatedAt?.apiToDateTime()
        txtId.text = "#${data.id}"
        txtPrice.text = if (data.currency == "IDR" || data.totalPrice == null) {
            data.totalPrice?.currencyFormatWithRp()
        } else {
            data.totalPrice.toString()
        }

        itemView.setOnClickListener {
            click?.invoke(data)
        }
    }
}