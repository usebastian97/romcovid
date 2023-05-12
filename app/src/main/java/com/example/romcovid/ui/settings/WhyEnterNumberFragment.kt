package com.example.romcovid.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.romcovid.R
import com.example.romcovid.databinding.FragmentWhyEnterNumberBinding

class WhyEnterNumberFragment : Fragment() {

    private lateinit var binding: FragmentWhyEnterNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentWhyEnterNumberBinding>(
            inflater,
            R.layout.fragment_why_enter_number,
            container,
            false
        )
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            backArrowIv.setOnClickListener {
                findNavController().popBackStack()
            }

            understandBtn.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }

}