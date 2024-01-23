import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.daa.workshop_ble.BleDevice
import ch.heigvd.daa.workshop_ble.R

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeDeviceListAdapter
    private var devices = mutableListOf<BleDevice>()

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner: BluetoothLeScanner?
        get() = bluetoothAdapter?.bluetoothLeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = LeDeviceListAdapter(devices)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button_refresh).setOnClickListener {
            devices.clear()
            adapter.notifyDataSetChanged()
            startBleScan()
        }

    }

    private fun startBleScan() {
        val scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                val device = result.device
                val bleDevice = BleDevice(device.name ?: "Unknown", device.address)
                if (bleDevice !in devices) {
                    devices.add(bleDevice)
                    adapter.notifyItemInserted(devices.size - 1)
                }
            }

            override fun onBatchScanResults(results: MutableList<ScanResult>) {
                // Handle batch scan results if needed
            }

            override fun onScanFailed(errorCode: Int) {
                // Handle scan failure
            }
        }

        bleScanner?.startScan(scanCallback)
    }
}
