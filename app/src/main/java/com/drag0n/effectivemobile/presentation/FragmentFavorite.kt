package com.drag0n.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.data.room.DataBase
import com.drag0n.effectivemobile.databinding.FragmentFavoriteBinding
import com.drag0n.effectivemobile.model.Vacancy
import com.drag0n.effectivemobile.presentation.adapters.VacanciesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFavorite : Fragment(), VacanciesAdapter.OnClickIsFavorite {
    private lateinit var binding: FragmentFavoriteBinding
    @Inject
    lateinit var db: DataBase
    val model: ViewModel by activityViewModels()
    private val list = mutableListOf<Vacancy>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcViewVacancies(view.context)



    }



    private fun initRcViewVacancies(context: Context) {
        val rcView2 = binding.rcView4
        db.Dao().getAllFlow().asLiveData().observe(viewLifecycleOwner){

            it.forEach { f->
                model.listVacascies.value?.forEach { l ->
                    if (f.id== l.id) list.add(l)
                }
            }

            val adapter = VacanciesAdapter(
                list, this)
            rcView2.layoutManager = LinearLayoutManager(context)
            rcView2.adapter = adapter
            val count = getVacancyText(list.size)
            binding.tvVacanseFavorite.text = count
        }






    }
    private fun getVacancyText(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "$count вакансии"
            else -> "$count вакансий"
        }
    }

    override fun onClick(vacancy: Vacancy, isFavorite: Boolean) {
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