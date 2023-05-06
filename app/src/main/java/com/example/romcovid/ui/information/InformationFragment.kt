package com.example.romcovid.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.romcovid.R
import com.example.romcovid.databinding.FragmentInformationBinding
import com.example.romcovid.viewmodel.InformationViewModel
import net.cachapa.expandablelayout.ExpandableLayout

class InformationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[InformationViewModel::class.java]

        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            questionContainer1.setOnClickListener(this@InformationFragment)
            questionContainer2.setOnClickListener(this@InformationFragment)
            questionContainer3.setOnClickListener(this@InformationFragment)
            questionContainer4.setOnClickListener(this@InformationFragment)
            questionContainer5.setOnClickListener(this@InformationFragment)
            questionContainer6.setOnClickListener(this@InformationFragment)
            questionContainer7.setOnClickListener(this@InformationFragment)
            questionContainer8.setOnClickListener(this@InformationFragment)
            questionContainer9.setOnClickListener(this@InformationFragment)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.questionContainer1 -> processViews(
                binding.expandableLayout1,
                binding.questionView1,
                binding.questionImage1
            )

            R.id.questionContainer2 -> processViews(
                binding.expandableLayout2,
                binding.questionView2,
                binding.questionImage2
            )

            R.id.questionContainer3 -> processViews(
                binding.expandableLayout3,
                binding.questionView3,
                binding.questionImage3
            )

            R.id.questionContainer4 -> processViews(
                binding.expandableLayout4,
                binding.questionView4,
                binding.questionImage4
            )

            R.id.questionContainer5 -> processViews(
                binding.expandableLayout5,
                binding.questionView5,
                binding.questionImage5
            )

            R.id.questionContainer6 -> processViews(
                binding.expandableLayout6,
                binding.questionView6,
                binding.questionImage6
            )

            R.id.questionContainer7 -> processViews(
                binding.expandableLayout7,
                binding.questionView7,
                binding.questionImage7
            )

            R.id.questionContainer8 -> processViews(
                binding.expandableLayout8,
                binding.questionView8,
                binding.questionImage8
            )

            R.id.questionContainer9 -> processViews(
                binding.expandableLayout9,
                binding.questionView9,
                binding.questionImage9
            )
        }

    }

    private fun processViews(layout: ExpandableLayout, textView: TextView, imageView: ImageView) {
        if (layout.isExpanded) {
            layout.collapse()
            textView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.primaryTextColor
                )
            )
            imageView.setImageResource(R.drawable.faq_colapse)
        } else {
            layout.expand()
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.hyperlinkColor))
            imageView.setImageResource(R.drawable.faq_expand)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}