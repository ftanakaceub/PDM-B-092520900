package com.example.cuidarmais.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidarmais.R
import com.example.cuidarmais.databinding.FragmentListMedicineBinding
import com.example.cuidarmais.ui.adapters.MedicineItemAdapter
import com.example.cuidarmais.ui.viewmodels.MedicineViewModel
import com.example.cuidarmais.ui.viewmodels.MedicineViewModelFactory
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory
import kotlin.math.round


class ListMedicineFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private val medicineViewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentListMedicineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController()
                .navigate(R.id.createMedicineFragment)
        }

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                medicineViewModel.list.observe(viewLifecycleOwner) { list ->
                    list?.let {
                        binding.recyclerView.adapter = MedicineItemAdapter(list)
                        binding.recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                    }
                }
                medicineViewModel.getMedicineList(user.id)
            }
        }
    }

}