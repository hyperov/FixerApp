package com.fixer.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fixer.app.R
import com.fixer.app.extensions.roundToTwoDecimalNumbers
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrenciesAdapter(
    private val data: ArrayList<Pair<String, Double>> = ArrayList(),
    private val onItemClick: (currency: Pair<String, Double>) -> Unit
) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount() = data.size

    fun add(list: List<Pair<String, Double>>?) {
        data.clear()
        data.addAll(list!!)
        notifyDataSetChanged()
    }


    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currency: Pair<String, Double>) = with(itemView) {

            with(currency) {
                tvCurrency.text = currency.first
                tvRate.text = currency.second.roundToTwoDecimalNumbers().toString()
            }

            setOnClickListener {
                onItemClick(currency)
            }
        }
    }
}