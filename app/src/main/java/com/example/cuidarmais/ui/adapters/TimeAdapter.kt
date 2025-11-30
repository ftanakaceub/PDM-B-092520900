package com.example.cuidarmais.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidarmais.databinding.TimeItemBinding
import java.time.LocalTime

class TimeAdapter(private val timeList: List<LocalTime>) :
    RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    inner class TimeViewHolder(val binding: TimeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(time: LocalTime) {
            binding.timeLabel.text = time.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TimeItemBinding.inflate(inflater, parent, false)
        return TimeViewHolder(binding)
    }

    override fun getItemCount(): Int = timeList.size

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(timeList[position])
    }
}