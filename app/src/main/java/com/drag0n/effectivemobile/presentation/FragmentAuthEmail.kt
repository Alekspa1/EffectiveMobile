package com.drag0n.effectivemobile.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.drag0n.effectivemobile.Const.KEY_INTENT_FRAGMENT
import com.drag0n.effectivemobile.R
import com.drag0n.effectivemobile.databinding.FragmentAuthEmailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAuthEmail : Fragment() {
private lateinit var binding: FragmentAuthEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            bNext.setOnClickListener {
                if (edEmail.text.isEmpty()) Toast.makeText(requireContext(), "Поле должно быть заполнено", Toast.LENGTH_SHORT).show()
                else {
                    val frag = FragmentAuthPass()
                    val bundle = Bundle()
                    bundle.putString(KEY_INTENT_FRAGMENT, edEmail.text.toString())
                    frag.arguments = bundle
                    parentFragmentManager.beginTransaction().replace(R.id.main, frag).commit()
                }
            }
        }
    }


}