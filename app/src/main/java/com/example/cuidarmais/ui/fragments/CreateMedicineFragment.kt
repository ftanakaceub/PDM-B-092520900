package com.example.cuidarmais.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidarmais.R
import com.example.cuidarmais.data.room.models.Medicine
import com.example.cuidarmais.databinding.FragmentCreateMedicineBinding
import com.example.cuidarmais.ui.adapters.TimeAdapter
import com.example.cuidarmais.ui.viewmodels.MedicineViewModel
import com.example.cuidarmais.ui.viewmodels.MedicineViewModelFactory
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory
import java.time.DayOfWeek
import java.time.LocalTime

class CreateMedicineFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private val medicineViewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentCreateMedicineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val timeList: ArrayList<LocalTime> = ArrayList()
        val adapter = TimeAdapter(timeList)

        binding.newTimeButton.setOnClickListener {
            binding.newTimeLayout.visibility = View.VISIBLE
            binding.newTimeButton.visibility = View.GONE
        }

        binding.btnSaveTime.setOnClickListener {
            val time = LocalTime.of(binding.timePicker.hour, binding.timePicker.minute)
            timeList.add(time)
            adapter.notifyItemInserted(timeList.size - 1)
            binding.newTimeLayout.visibility = View.GONE
            binding.newTimeButton.visibility = View.VISIBLE
        }

        binding.listTimes.adapter = adapter
        binding.listTimes.layoutManager=LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        userViewModel.user.observe(viewLifecycleOwner) {user ->
            user?.let {
                binding.saveMedicineButton.setOnClickListener {
                    val name = binding.editName.text.toString().trim()
                    val dosage = binding.editDosage.text.toString().trim()
                    val dayOfWeek = ArrayList<DayOfWeek>()
                    if (binding.radioButtonSunday.isChecked) dayOfWeek.add(DayOfWeek.SUNDAY)
                    if (binding.radioButtonMonday.isChecked) dayOfWeek.add(DayOfWeek.MONDAY)
                    if (binding.radioButtonTuesday.isChecked) dayOfWeek.add(DayOfWeek.TUESDAY)
                    if (binding.radioButtonWednesday.isChecked) dayOfWeek.add(DayOfWeek.WEDNESDAY)
                    if (binding.radioButtonThursday.isChecked) dayOfWeek.add(DayOfWeek.THURSDAY)
                    if (binding.radioButtonFriday.isChecked) dayOfWeek.add(DayOfWeek.FRIDAY)
                    if (binding.radioButtonSaturday.isChecked) dayOfWeek.add(DayOfWeek.SATURDAY)
                    val medicine = Medicine(0, user.id, name, dayOfWeek, timeList, dosage, "")
                    medicineViewModel.insertMedicine(medicine)
                    findNavController().navigate(R.id.appFragment)
                }
            }
        }
    }
}