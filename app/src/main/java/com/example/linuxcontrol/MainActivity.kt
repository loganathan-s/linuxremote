package com.example.linuxcontrol

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.linuxcontrol.adapters.DeviceAdapter
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
//    protected val deviceLists: MutableList<BluetoothDevice> = ArrayList()
    protected val deviceLists: MutableList<String> = ArrayList()
    protected var deviceAdapter: DeviceAdapter? = null
    var REQUEST_BLUETOOTH = 1
    protected var bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter();
    private var broadcastReceiver = null
    private var startDiscovery = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        deviceLists.add("Logan1")
        deviceLists.add("Logan2")
        deviceAdapter = DeviceAdapter(this, deviceLists)
        initRecyclerView()
//        getPairedDevice()
    }

//    private fun getPairedDevice() {
//        if (bluetoothAdapter == null) {
//            AlertDialog.Builder(this@MainActivity)
//                .setTitle("Not compatible")
//                .setMessage("Your phone does not support Bluetooth")
//                .setPositiveButton("Exit",
//                    DialogInterface.OnClickListener { dialog, which -> System.exit(0) })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show()
//        } else if (!bluetoothAdapter!!.isEnabled()) {
//            bluetoothAdapter?.enable();
//            // DOnt prompt user for bluetooth actiivatiion
//            val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            startActivityForResult(enableBT, REQUEST_BLUETOOTH)
//        } else {
//            val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter!!.getBondedDevices()
//            if (!pairedDevices.isEmpty() && pairedDevices.size > 0) {
//                for (device in pairedDevices) {
//                    deviceLists.add(device)
//                    if (device.name == "g2r-stage2") {
//                        startDiscovery = false
//                    }
//                }
//            }
//        }
//    }

//    override fun onStart() {
//        super.onStart()
//        val bluetoothFilter = IntentFilter()
//        bluetoothFilter.addAction(BluetoothDevice.ACTION_FOUND);
////        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
////        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        deviceAdapter = DeviceAdapter(this, deviceLists)
//        if(startDiscovery) {
//            registerReceiver(mReceiver, bluetoothFilter)
//            Toast.makeText(applicationContext, "startDiscovery - ACTION_FOUND", Toast.LENGTH_SHORT).show()
//            bluetoothAdapter?.startDiscovery()
//        } else {
//            Toast.makeText(applicationContext, "Device PAIRED ALREADY - ACTION_FOUND", Toast.LENGTH_SHORT).show()
//        }
//        val title: TextView = findViewById(R.id.headerTextView)
//        title.text = "Available Rooms"
//        initRecyclerView()
//    }

//    override fun onPause() {
//        super.onPause()
//        if(startDiscovery) {
//            Toast.makeText(applicationContext, "cancelDiscovery - onPause", Toast.LENGTH_SHORT).show()
//            bluetoothAdapter?.cancelDiscovery()
//            unregisterReceiver(mReceiver);
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        if(startDiscovery) {
//            Toast.makeText(applicationContext, "startDiscovery - onResume", Toast.LENGTH_SHORT).show()
//            bluetoothAdapter?.startDiscovery()
//        }
//    }

//    // Create a BroadcastReceiver for ACTION_FOUND.
//    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent) {
//            val action = intent.action
//            Toast.makeText(applicationContext, "bluetoothReceiver - ACTION_FOUND", Toast.LENGTH_SHORT).show()
//            if (BluetoothDevice.ACTION_FOUND == action) {
//                Toast.makeText(applicationContext, "bluetoothReceiver - ACTION_FOUND", Toast.LENGTH_SHORT).show()
//                Log.e("bluetoothReceiver", "ACTION_FOUND")
//                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
//                val deviceName = device.name
//                if(deviceName.length > 1) {
//                    deviceLists += device
//                    deviceAdapter?.addAll(deviceLists)
//                    deviceAdapter?.notifyDataSetChanged()
//                    if (deviceName.equals("g2r-stage2")) {
//                        Toast.makeText(applicationContext, "Device FOUND cancel discovery", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == action) {
//                Toast.makeText(applicationContext, "bluetoothReceiver - ACTION_DISCOVERY_STARTED", Toast.LENGTH_SHORT).show()
//            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
//                Toast.makeText(applicationContext, "bluetoothReceiver - ACTION_DISCOVERY_FINISHED", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.RoomLists)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = deviceAdapter
    }
}

