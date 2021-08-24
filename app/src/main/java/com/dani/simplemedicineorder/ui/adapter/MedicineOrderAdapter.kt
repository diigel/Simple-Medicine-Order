package com.dani.simplemedicineorder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.simplemedicineorder.data.entity.ResponseMedicineOrderList
import com.dani.simplemedicineorder.databinding.ItemListMedicineBinding
import com.dani.simplemedicineorder.ui.viewholder.MedicineOrderViewHolder

class MedicineOrderAdapter : RecyclerView.Adapter<MedicineOrderViewHolder>() {

    private val dataMedicine : MutableList<ResponseMedicineOrderList> = mutableListOf()
    private var click :((data : ResponseMedicineOrderList) -> Unit)? = null

    fun onClick(click : (data : ResponseMedicineOrderList) -> Unit){
        this.click = click
    }

    fun addList(data : List<ResponseMedicineOrderList>){
        dataMedicine.clear()
        dataMedicine.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineOrderViewHolder {
        return MedicineOrderViewHolder(ItemListMedicineBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MedicineOrderViewHolder, position: Int) {
        holder.bind(dataMedicine[position],click)
    }

    override fun getItemCount(): Int {
        return dataMedicine.size
    }
}