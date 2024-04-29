package com.example.supreme_car_wash.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.databinding.FragmentCitasBinding


class CitasFragment : Fragment() {

    private lateinit var binding : FragmentCitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCitasBinding.inflate(inflater, container,  false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CitasFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}