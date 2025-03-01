package com.drag0n.effectivemobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.databinding.ItemOffersBinding
import com.drag0n.effectivemobile.model.Offer

class OffersAdaprer(private val offers: List<Offer>, private val onClick: OnClick) :
    RecyclerView.Adapter<OffersAdaprer.Holder>() {
    class Holder(item: View, private val onClick: OnClick) : RecyclerView.ViewHolder(item) {

        private val binding = ItemOffersBinding.bind(item)
        fun bind(offer: Offer) {
            with(binding) {
                tvOffers.text = offer.title.trimIndent()
                if (offer.button == null) tvButton.visibility = View.GONE
                if(offer.id != null) {
                    when (offer.id) {
                        "near_vacancies" -> imOffers.setImageResource(R.drawable.avatars)
                        "level_up_resume" -> imOffers.setImageResource(R.drawable.ellipse)
                        "temporary_job" -> imOffers.setImageResource(R.drawable.ava)
                        else -> imOffers.visibility = View.GONE
                    }
                } else imOffers.visibility = View.GONE
                root.setOnClickListener { onClick.onClick(offer.link)  }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_offers, parent, false)
        return Holder(view, onClick)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(offers[position])
    }

    interface OnClick{
        fun onClick(value: String)
    }
}

