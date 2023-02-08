package org.cazait.cazait_android.ui.viewmodel

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.cazait.cazait_android.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    private val _locationPermissionGranted = MutableLiveData<Boolean>()
    val locationPermissionGranted: LiveData<Boolean>
        get() = _locationPermissionGranted

    fun requestLocationPermission(activity: Activity) {
        val permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            _locationPermissionGranted.value = true
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            _locationPermissionGranted.value = true
        } else {

        }
    }

    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}