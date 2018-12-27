package com.corp.k.androidos.databinding

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.corp.k.androidos.R

open class BindingAdapter : RecyclerView.Adapter<BindingAdapter.BindingHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BindingAdapter.BindingHolder {
        val itemBinding : BindingItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.binding_item, null, false)
        return BindingHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return 10;
    }

    override fun onBindViewHolder(holder: BindingAdapter.BindingHolder, position: Int) {
        holder.bindView(position)
    }

    open class BindingHolder(val ItemUserBinding : BindingItemBinding) : RecyclerView.ViewHolder(ItemUserBinding.root) {
        open val txtName : ObservableField<String> = ObservableField<String>();

        fun bindView(position: Int){
            if(ItemUserBinding.viewholder == null){
                ItemUserBinding.viewholder = this
            }
            txtName.set(position.toString())
        }
    }
}