package com.example.cuidarmais.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cuidarmais.data.room.daos.MedicineDao
import com.example.cuidarmais.data.room.models.Medicine
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class MedicineRepository(
    private val medicineDao: MedicineDao
) {

    suspend fun getMedicineList(userId: Int): List<Medicine> {
        return medicineDao.getMedicineList(userId)
    }

    suspend fun insertMedicine(medicine: Medicine) {
        medicineDao.insert(medicine)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getNextMedicine(userId: Int): Medicine? {
        val medicineList = getMedicineList(userId)

        val today = LocalDateTime.now()

        val todayMedicines =
            medicineList.filter { med -> med.daysOfWeek.contains(DayOfWeek.of(today.dayOfWeek.value)) }
        val upcomingMedicine =
            todayMedicines.filter { med -> med.times.any { it -> it.isAfter(LocalTime.now()) } }
        if (upcomingMedicine.isEmpty()) return null
        if (upcomingMedicine.size == 1) return upcomingMedicine[0]
        var nextMedicine: Medicine? = null
        var minInterval: Int = 9999999
        for (medicine in upcomingMedicine) {
            for (time in medicine.times) {
                val interval = time.toSecondOfDay() - LocalTime.now().toSecondOfDay()
                if (interval < minInterval) {
                    minInterval = interval
                    nextMedicine = medicine
                }
            }
        }
        return nextMedicine
    }
}