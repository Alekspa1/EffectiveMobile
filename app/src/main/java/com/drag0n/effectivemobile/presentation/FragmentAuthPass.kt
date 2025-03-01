package com.drag0n.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.drag0n.effectivemobile.Const.KEY_INTENT_FRAGMENT
import com.drag0n.effectivemobile.R

import com.drag0n.effectivemobile.databinding.FragmentAuthPassBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAuthPass : Fragment() {
    private lateinit var binding: FragmentAuthPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = "${binding.tvEmail.text} ${arguments?.getString(KEY_INTENT_FRAGMENT)}"
        binding.tvEmail.text = value
        focusEdText(binding)
    }

    private fun focusEdText(binding: FragmentAuthPassBinding){
        with(binding){
            edOne.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) { // Если введен один символ
                        edTwo.requestFocus() // Переводим фокус на editText2
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            // Настройка TextWatcher для editText2
            edTwo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) { // Если введен один символ
                        edThree.requestFocus() // Переводим фокус на editText3
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            // Настройка TextWatcher для editText3
            edThree.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) { // Если введен один символ
                       edFour.requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            edFour.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) { // Если введен один символ
                        hideKeyboard()
                        parentFragmentManager.beginTransaction().replace(R.id.main, FragmentStart()).commit()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}