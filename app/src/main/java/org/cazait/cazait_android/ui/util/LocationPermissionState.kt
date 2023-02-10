package org.cazait.cazait_android.ui.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/*
 * Apps targeting Android 12 and later cannot request only ACCESS_FINE_LOCATION. They must also
 * request ACCESS_COARSE_LOCATION, and the user has the option to grant approximate location only.
 * See https://developer.android.com/training/location/permissions#approximate-request
 *
 * Apps targeting older platform versions can still request both and the system will ask for
 * ACCESS_FINE_LOCATION, since holding that also implies holding ACCESS_COARSE_LOCATION.
 */
private val locationPermissions =
    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

internal fun Context.hasPermission(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

internal fun Activity.shouldShowRationaleFor(permission: String): Boolean =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

/**
 * State holder for location permissions. Properties are implemented as State objects so that they
 * trigger a recomposition when the value changes (if the value is read within a Composable scope).
 * This also implements the behavior for requesting location permissions and updating the internal
 * state afterward.
 *
 * This class should be initialized in `onCreate()` of the Activity. Sample usage:
 *
 * ```
 * override fun onCreate(savedInstanceState: Bundle?) {
 *     super.onCreate(savedInstanceState)
 *
 *     val locationPermissionState = LocationPermissionState(this) { permissionState ->
 *         if (permissionState.accessFineLocationGranted) {
 *             // Do something requiring precise location permission
 *         }
 *     }
 *
 *     setContent {
 *         Button(
 *             onClick = { locationPermissionState.requestPermissions() }
 *         ) {
 *             Text("Click Me")
 *         }
 *     }
 * }
 * ```
 */

class LocationPermissionState(
    private val activity: ComponentActivity,
    private val onResult: (LocationPermissionState) -> Unit
) {
    /** Whether permission was granted to access approximate location. */
    var accessCoarseLocationGranted by mutableStateOf(false)
        private set

    /** Whether to show a rationale for permission to access approximate location. */
    var accessCoarseLocationNeedsRationale by mutableStateOf(false)
        private set

    /** Whether permission was granted to access precise location. */
    var accessFineLocationGranted by mutableStateOf(false)
        private set

    /** Whether to show a rationale for permission to access precise location. */
    var accessFineLocationNeedsRationale by mutableStateOf(false)
        private set

    /**
     * Whether permission has been requested at least once. Note that this is not persisted across
     * Activity recreation or app restarts, but it can be useful as a hint for the UI.
     */
    var permissionRequested by mutableStateOf(false)
        private set

    private val permissionLauncher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            permissionRequested = true
            updateState()
            onResult(this)
        }

    init {
        updateState()
    }

    private fun updateState() {
        accessCoarseLocationGranted = activity.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        accessCoarseLocationNeedsRationale =
            activity.shouldShowRationaleFor(Manifest.permission.ACCESS_COARSE_LOCATION)
        accessFineLocationGranted = activity.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        accessFineLocationNeedsRationale =
            activity.shouldShowRationaleFor(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * Launch the permission request. Note that this may or may not show the permission UI if the
     * permission has already been granted or if the user has denied permission multiple times.
     */
    fun requestPermissions() {
        permissionLauncher.launch(locationPermissions)
    }

    fun shouldShowRationale(): Boolean {
        /*
         * On Android 12 and later, the user could choose to grant access only to approximate
         * location. The user could also deny both permissions, and the system may tell us to show a
         * rationale for either or both permissions.
         *
         * Since we require precise location, we'll show the rationale if the system tells us to
         * show one for either permission, and we'll also show it if permission was requested but
         * precise location was not granted.
         */
        return accessCoarseLocationNeedsRationale || accessFineLocationNeedsRationale ||
                (permissionRequested && !accessFineLocationGranted)
    }
}