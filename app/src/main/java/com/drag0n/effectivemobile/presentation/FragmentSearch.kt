package com.drag0n.effectivemobile.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.databinding.FragmentSearchBinding
import com.drag0n.effectivemobile.model.Vacancy
import com.drag0n.effectivemobile.presentation.adapters.OffersAdaprer
import com.drag0n.effectivemobile.presentation.adapters.VacanciesAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSearch : Fragment(), OffersAdaprer.OnClick, VacanciesAdapter.OnClickIsFavorite {
    private lateinit var binding: FragmentSearchBinding
    val model: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRcViewOffers(view.context)
        initRcViewVacancies(view.context)
        val count = "Еще ${getVacancyText(model.getCount())}"
        binding.button.text = count
        binding.button.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.container, FragmentVacancies()).commit()
        }

    }



    private fun initRcViewOffers(context: Context) {
        val rcView = binding.rcView
        model.listOffers.observe(viewLifecycleOwner){
            val adapter = OffersAdaprer(it, this)
            rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rcView.adapter = adapter
        }


    }

    private fun initRcViewVacancies(context: Context) {
        val rcView2 = binding.rcView2
        model.listVacascies.observe(viewLifecycleOwner){
            val adapter = VacanciesAdapter(
                it.take(3), this)
            rcView2.layoutManager = LinearLayoutManager(context)
            rcView2.adapter = adapter
        }


    }

    override fun onClick(value: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(value)
            )
        )
    }
    private fun getVacancyText(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

    override fun onClick(vacancy: Vacancy, isFavorite: Boolean) {
        model.getAll()
       if (isFavorite) model.insertVacancy(vacancy)
        else model.deleteVacancy(vacancy)
    }

    override fun isFavorite(id: String): Boolean {
        model.getAll()
        val state = model.listIsFavorite.value?.map { it.id }
        val result: Boolean = state?.contains(id) ?: false
        return result
    }
}