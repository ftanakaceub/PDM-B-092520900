package com.example.cuidarmais.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidarmais.data.room.models.Medicine
import com.example.cuidarmais.databinding.MedicineItemBinding
import java.time.DayOfWeek

class MedicineItemAdapter(private val medicineList: List<Medicine>) :
    RecyclerView.Adapter<MedicineItemAdapter.MedicineViewHolder>() {

    inner class MedicineViewHolder(val binding: MedicineItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(medicine: Medicine) {
            binding.labelMedicineName.text = medicine.name
            binding.labelMedicineTime.text =
                medicine.times.sorted().joinToString(", ") { it -> it.toString() }
            binding.radioButtonSunday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.SUNDAY)
            binding.radioButtonMonday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.MONDAY)
            binding.radioButtonTuesday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.TUESDAY)
            binding.radioButtonWednesday.isChecked =
                medicine.daysOfWeek.contains(DayOfWeek.WEDNESDAY)
            binding.radioButtonThursday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.THURSDAY)
            binding.radioButtonFriday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.FRIDAY)
            binding.radioButtonSaturday.isChecked = medicine.daysOfWeek.contains(DayOfWeek.SATURDAY)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MedicineItemBinding.inflate(inflater, parent, false)
        return MedicineViewHolder(binding)
    }

    override fun getItemCount(): Int = medicineList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(medicineList[position])
    }
}