package com.example.supreme_car_wash.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.supreme_car_wash.R
import com.example.supreme_car_wash.activities.LoginActivity
import com.example.supreme_car_wash.activities.NuevoVehiculoActivity
import com.example.supreme_car_wash.databinding.FragmentPerfilBinding
import com.example.supreme_car_wash.responses.ClienteResponse


class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
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
        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        binding.btnCerrarSesion.setOnClickListener {

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        }

        binding.btnAgregarVehiculo.setOnClickListener {
            val intent = Intent(activity, NuevoVehiculoActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance(cliente: ClienteResponse) =
            PerfilFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}