package com.drag0n.effectivemobile.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.databinding.ItemVacanciesBinding
import com.drag0n.effectivemobile.model.Vacancy
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class VacanciesAdapter(
    private val offers: List<Vacancy>,
    private val onClickIsFavorite: OnClickIsFavorite
) :
    RecyclerView.Adapter<VacanciesAdapter.Holder>() {

    class Holder(item: View, private val onClickIsFavorite: OnClickIsFavorite) :
        RecyclerView.ViewHolder(item) {
        private val binding = ItemVacanciesBinding.bind(item)
        fun bind(vacancy: Vacancy) {
            with(binding) {
                val date = "Опубликовано ${formatDate(vacancy.publishedDate)}"

                if (vacancy.lookingNumber != null) {
                    val look = "Сейчас просматривает ${getLockingText(vacancy.lookingNumber)}"
                    tvLooking.text = look
                } else tvLooking.visibility = View.INVISIBLE
                tvTitle.text = vacancy.title
                tvCity.text = vacancy.address.town
                tvCompany.text = vacancy.company
                tvExperience.text = vacancy.experience.previewText
                tvDate.text = date

                when(onClickIsFavorite.isFavorite(vacancy.id)){
                    true -> binding.imFavority.setImageResource(R.drawable.is_favorite_true)
                    false -> binding.imFavority.setImageResource(R.drawable.ic_favotity)
                }


                imFavority.setOnClickListener {
                isFavoriteItem(onClickIsFavorite.isFavorite(vacancy.id), vacancy)
                }


            }

        }

        private fun getLockingText(count: Int): String {
            return when {
                count % 10 in 2..4 && count % 100 !in 12..14 -> "$count человека"
                else -> "$count человек"
            }
        }

        private fun formatDate(inputDate: String): String {
            val date = LocalDate.parse(inputDate)
            val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
            return date.format(formatter)
        }

        private fun isFavoriteItem(isFavorite: Boolean, vacancy: Vacancy){
            when(isFavorite){
                false -> {
                    binding.imFavority.setImageResource(R.drawable.is_favorite_true)
                    onClickIsFavorite.onClick(vacancy, true)
                }
                true -> {
                    binding.imFavority.setImageResource(R.drawable.ic_favotity)
                    onClickIsFavorite.onClick(vacancy, false)
                }
            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_vacancies, parent, false)
        return Holder(view, onClickIsFavorite)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(offers[position])
    }

    interface OnClickIsFavorite {
        fun onClick(vacancy: Vacancy, isFavorite: Boolean)
        fun isFavorite(id: String) : Boolean
    }
}

