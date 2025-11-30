package com.example.cuidarmais.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.cuidarmais.R
import com.example.cuidarmais.databinding.FragmentHomeBinding
import com.example.cuidarmais.ui.viewmodels.MedicineViewModel
import com.example.cuidarmais.ui.viewmodels.MedicineViewModelFactory
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory


class HomeFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private val medicineViewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicineViewModel.nextMedicine.observe(viewLifecycleOwner) { medicine ->
            medicine?.let {
                binding.labelNextMedicineName.text = medicine.name
                binding.nextMedicineCard.visibility = View.VISIBLE
            }
        }

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.labelGreetings.text = "Olá, ${user.name}"
                medicineViewModel.getNextMedicine(user.id)
            }
        }
    }

}