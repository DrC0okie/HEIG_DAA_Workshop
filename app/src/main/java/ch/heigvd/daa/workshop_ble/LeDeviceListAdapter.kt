package ch.heigvd.daa.workshop_ble

import android.bluetooth.BluetoothDevice
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeDeviceListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val devices = mutableListOf<BluetoothDevice>()

    fun addDevice(device: BluetoothDevice) {
        if (!devices.contains(device)) {
            devices.add(device)
            notifyItemInserted(devices.size - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Create your view holder here
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Bind the Bluetooth device to your view holder
    }

    override fun getItemCount(): Int = devices.size
}