package com.klinserg.news.bluetooth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.klinserg.news.bluetooth.ui.DeviceScreen
import com.klinserg.news.bluetooth.ui.PermissionsRequiredScreen
import com.klinserg.news.bluetooth.ui.ScanningScreen
import com.klinserg.news.bluetooth.ui.haveAllPermissions
import com.klinserg.news.bluetooth.viewmodels.BLEClientViewModel

@SuppressLint("MissingPermission")
@Composable
fun BluetoothScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    viewModel: BLEClientViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var allPermissionsGranted by remember {
        mutableStateOf(haveAllPermissions(context))
    }

    Box(modifier = Modifier.statusBarsPadding()) {
        if (!allPermissionsGranted) {
            PermissionsRequiredScreen { allPermissionsGranted = true }
        } else if (uiState.activeDevice == null) {
            ScanningScreen(
                isScanning = uiState.isScanning,
                foundDevices = uiState.foundDevices,
                startScanning = viewModel::startScanning,
                stopScanning = viewModel::stopScanning,
                selectDevice = { device ->
                    viewModel.stopScanning()
                    viewModel.setActiveDevice(device)
                }
            )
        } else {
            DeviceScreen(
                unselectDevice = {
                    viewModel.disconnectActiveDevice()
                    viewModel.setActiveDevice(null)
                },
                isDeviceConnected = uiState.isDeviceConnected,
                discoveredCharacteristics = uiState.discoveredCharacteristics,
                password = uiState.password,
                nameWrittenTimes = uiState.nameWrittenTimes,
                connect = viewModel::connectActiveDevice,
                discoverServices = viewModel::discoverActiveDeviceServices,
                readPassword = viewModel::readPasswordFromActiveDevice,
                writeName = viewModel::writeNameToActiveDevice
            )
        }
    }
}
