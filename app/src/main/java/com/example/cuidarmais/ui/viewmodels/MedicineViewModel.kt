package com.example.cuidarmais.ui.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cuidarmais.data.repositories.MedicineRepository
import com.example.cuidarmais.data.room.db.AppDatabase
import com.example.cuidarmais.data.room.models.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application) : ViewModel() {

    private val repository: MedicineRepository
    private var _list: MutableLiveData<List<Medicine>> = MutableLiveData()
    val list: LiveData<List<Medicine>> = _list
    private var _nextMedicine: MutableLiveData<Medicine?> = MutableLiveData()
    val nextMedicine: LiveData<Medicine?> = _nextMedicine


    init {
        val medicineDao = AppDatabase.getInstance(application).getMedicineDao()
        repository = MedicineRepository(medicineDao)
        _list.postValue(ArrayList())
        _nextMedicine.postValue(null)
    }

    fun getMedicineList(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _list.postValue(repository.getMedicineList(userId))
        }
    }

    fun insertMedicine(medicine: Medicine) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMedicine(medicine)
            repository.getMedicineList(medicine.userId)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getNextMedicine(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _nextMedicine.postValue(repository.getNextMedicine(userId))
        }
    }
}

class MedicineViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicineViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
