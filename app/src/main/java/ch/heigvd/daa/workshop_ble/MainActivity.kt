package ch.heigvd.daa.workshop_ble

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeDeviceListAdapter
    private lateinit var scanCallback: ScanCallback
    private lateinit var progressBar: ProgressBar
    private var devices = mutableListOf<BleDevice>()
    private val granted = PackageManager.PERMISSION_GRANTED
    private val accessLoc = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val REQUEST_PERMISSIONS_CODE = 1

    // Get the bluetooth manager
    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    // Runtime checked permissions
    private val requiredPermissions = arrayOf(
        accessLoc,
        android.Manifest.permission.BLUETOOTH_SCAN,
        android.Manifest.permission.BLUETOOTH_CONNECT
    )

    private val bleScanner: BluetoothLeScanner?
        get() = bluetoothAdapter?.bluetoothLeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = LeDeviceListAdapter(devices)
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progress_bar)
        findViewById<Button>(R.id.button_refresh).setOnClickListener {
            devices.clear()
            adapter.notifyDataSetChanged()
            startBleScan()
        }
    }

    private fun startBleScan() {
        if (requiredPermissions.any { checkSelfPermission(it) != granted }) {
            // Request permissions, then start scan
            requestPermissions(requiredPermissions, REQUEST_PERMISSIONS_CODE)
        } else {
            // Permissions granted, define callback, then start scan
            val scanCallback = object : ScanCallback() {
                override fun onScanResult(callbackType: Int, result: ScanResult) {

                    if (checkSelfPermission(accessLoc) != granted) {
                        // Handle the case where permission is not granted
                        return
                    }

                    val device = result.device
                    val bleDevice = BleDevice(device.name ?: "Unknown", device.address)
                    if (bleDevice !in devices) {
                        adapter.devices.add(bleDevice)
                        adapter.notifyItemInserted(adapter.devices.size - 1)
                    }
                }
                // List of scan results that are previously scanned.
                override fun onBatchScanResults(results: MutableList<ScanResult>) {}

                // Handle scan failure
                override fun onScanFailed(errorCode: Int) {}
            }
            // Show progress bar
            progressBar.visibility = View.VISIBLE

            // Start the scan
            bleScanner?.startScan(scanCallback)

            // Stop the scan after 10 seconds
            Handler(Looper.getMainLooper()).postDelayed({ stopBleScan()}, 10000) // 10 seconds
        }
    }

    private fun stopBleScan() {
        if (::scanCallback.isInitialized && checkSelfPermission(accessLoc) == granted) {
            bleScanner?.stopScan(scanCallback)
        }
        // Hide the ProgressBar
        progressBar.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == granted) {
                    // Permission granted, start the scan
                    startBleScan()
                } else {
                    // Permission denied, handle the failure
                }
            }
        }
    }
}
