package com.drag0n.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.data.room.DataBase

import com.drag0n.effectivemobile.databinding.FragmentStartBinding
import com.drag0n.effectivemobile.model.JsonFileOffers
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class FragmentStart : Fragment() {
    private lateinit var binding: FragmentStartBinding
    val model: ViewModel by activityViewModels()
    @Inject
    lateinit var db: DataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction().replace(R.id.container, FragmentSearch()).commit()
        val badge = binding.bNav.getOrCreateBadge(R.id.isFavotite)
        db.Dao().getAllFlow().asLiveData().observe(viewLifecycleOwner){
                if (it.isEmpty()) badge.isVisible = false
            else {
                    badge.isVisible = true
                    badge.number = it.size
                }

        }
    with(binding){
        bNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.search -> openFrag(FragmentSearch())
                R.id.isFavotite -> openFrag(FragmentFavorite())
                R.id.response -> toast(requireContext())
                R.id.message -> toast(requireContext())
                R.id.profile -> toast(requireContext())
            }
            true
        }



    }
    }
    private fun toast(context: Context){
        Toast.makeText(context, "Раздел еще не готов", Toast.LENGTH_SHORT).show()
        binding.container.visibility = View.GONE
    }
    private fun openFrag(frag: Fragment){
        binding.container.visibility = View.VISIBLE
        parentFragmentManager.beginTransaction().replace(R.id.container,frag).commit()
    }



}