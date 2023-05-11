package com.example.romcovid.ui.settings.bmi

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.romcovid.R
import com.example.romcovid.databinding.FragmentBmiBinding

class BmiFragment : Fragment() {

    private var height: Float = 0F
    private var weight: Float = 0F
    private var countWeight = 50
    private var countAge = 20
    private var chosen: Boolean = true

    private lateinit var viewModel: BmiViewModel

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        initComponents()

        return binding.root
    }

    private fun incrementWithLimit(currentValue: Int, increment: Int, maxLimit: Int): Int {
        val newValue = currentValue + increment
        return if (newValue > maxLimit) {
            maxLimit
        } else {
            newValue
        }
    }

    private fun decrementWithLimit(currentValue: Int, decrement: Int, minLimit: Int): Int {
        val newValue = currentValue - decrement
        return if (newValue < minLimit) {
            minLimit
        } else {
            newValue
        }
    }

    private fun initComponents() {
        binding.apply {
            Seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val ht = progress.toString() + resources.getString(R.string.cm)
                    binding.heightTxt.text = ht
                    height = (progress.toFloat() / 100)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            // TODO - De facut increment la apasarea continua a butonului --!!
            weightPlus.setOnClickListener {
                if (countWeight > 300) {
                    incrementWithLimit(0, 1, 300)
                    Toast.makeText(context, R.string.weight_300, Toast.LENGTH_LONG).show()
                } else {
                    binding.weightTxt.text = countWeight++.toString()
                }
            }

            // TODO - De facut decrement la apasarea continua a butonului --!!
            weightMinus.setOnClickListener {
                if (countWeight < 1) {
                    decrementWithLimit(0, 1, 0)
                    Toast.makeText(context, R.string.weight_zero, Toast.LENGTH_LONG).show()
                } else {
                    binding.weightTxt.text = countWeight--.toString()
                }
            }

            agePlus.setOnClickListener {
                if (countAge > 100) {
                    incrementWithLimit(0, 1, 100)
                    Toast.makeText(context, R.string.age_100, Toast.LENGTH_LONG).show()
                } else {
                    binding.age.text = countAge++.toString()
                }
            }

            // TODO - De facut decrement la apasarea continua a butonului --!!
            ageMinus.setOnClickListener {
                if (countAge < 1) {
                    decrementWithLimit(0, 1, 0)
                    Toast.makeText(context, R.string.age_zero, Toast.LENGTH_LONG).show()
                } else {
                    binding.age.text = countAge--.toString()
                }
            }

            calculate.setOnClickListener {
                if (height.equals(0f)) {
                    Toast.makeText(context, R.string.cannot_zero, Toast.LENGTH_LONG).show()
                } else if (!chosen) {
                    cardViewFemale.isEnabled = true
                    cardViewMale.isEnabled = true
                    Toast.makeText(context, R.string.choose_gender, Toast.LENGTH_LONG).show()
                } else {
                    weight = binding.weightTxt.text.toString().toFloat()
                    calculateBMI(age.text.toString())
                }
            }

            // TODO -- setup card background color when is selected -- !!
            cardViewMale.setOnClickListener {
                if (chosen) {
                    maleTxt.setTextColor(Color.parseColor("#FFFFFF"))
                    cardViewFemale.isEnabled = false
                    chosen = false

                } else {
                    maleTxt.setTextColor(Color.parseColor("#8D8E99"))
                    cardViewFemale.isEnabled = true
                    chosen = true
                }
            }

            cardViewFemale.setOnClickListener {
                if (chosen) {
                    femaleTxt.setTextColor(Color.parseColor("#FFFFFF"))
                    cardViewMale.isEnabled = false
                    chosen = false

                } else {
                    femaleTxt.setTextColor(Color.parseColor("#8D8E99"))
                    cardViewMale.isEnabled = true
                    chosen = true
                }
            }
        }
    }

    private fun calculateBMI(age: String) {
        val bmi = weight / (height * height)
        val action = BmiFragmentDirections.navigateToResult(bmi, age)
        findNavController().navigate(action)
    }

}