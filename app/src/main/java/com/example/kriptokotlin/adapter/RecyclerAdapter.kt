package com.example.kriptokotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.kriptokotlin.databinding.RowLayoutBinding
import com.example.kriptokotlin.model.CryptoModel

class RecyclerAdapter(private val cryptoList : ArrayList<CryptoModel>, private val listener : Listener)
    : RecyclerView.Adapter<RecyclerAdapter.RowHolder>() {

    private val colors : Array<String> = arrayOf("#82E0AA","#F7DC6F","#A569BD","#85C1E9","#F9E79F","#F1948A")

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    class RowHolder(private val rowLayoutBinding: RowLayoutBinding) : RecyclerView.ViewHolder(rowLayoutBinding.root) {


        fun bind(cryptoModel : CryptoModel, colors:Array<String>, position: Int, listener:Listener){

            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%6]))
            rowLayoutBinding.textCurrency.text = cryptoModel.currency
            rowLayoutBinding.textPrice.text = cryptoModel.price

        }
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        var rowLayoutBinding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(rowLayoutBinding)

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}