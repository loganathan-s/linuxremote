package com.example.linuxcontrol.adapters

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.ParcelUuid
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.linuxcontrol.ControlActivity
import com.example.linuxcontrol.R
import org.json.JSONObject


class DeviceAdapter(
    val mContext: Context,
   //val deviceData: MutableList<BluetoothDevice>
    val deviceData: MutableList<String>
) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {
    // Create new views (invoked by the layout manager)
    var connectedDevice = null;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(">>>", "---------------------------onCreateViewHolder CALLED------------------------------")
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_available_bluetooth_devices, parent, false)
        return ViewHolder(view)
    }
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.textView.text = myDataset[position]
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val device: BluetoothDevice = this.deviceData[position]
        val device: String = this.deviceData[position]
//        val deviceNameText: String = device.name
//        val deviceUUID: Boolean = device.fetchUuidsWithSdp();
//        holder.name.text = deviceNameText;
        holder.name.text = device;
        holder.deviceListsLayout.setOnClickListener {
            Toast.makeText(mContext, "onBindViewHolder" + device, Toast.LENGTH_SHORT).show()
//            Toast.makeText(mContext, "onBindViewHolder" + deviceNameText, Toast.LENGTH_SHORT).show()
            Toast.makeText(mContext, "creating connection", Toast.LENGTH_SHORT).show()
            try {
//                val selectedDevice: BluetoothDevice = device
                val intent = Intent(mContext, ControlActivity::class.java)
//                intent.putExtra("device", device)
                mContext.startActivity(intent)
//
//                val uuids: Array<ParcelUuid> = device.getUuids()
//                Toast.makeText(mContext, uuids[0].getUuid().toString(), Toast.LENGTH_SHORT).show()
//                val socket: BluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(uuids[0].getUuid());
//                socket.connect()
//                if (socket.isConnected()) {
//                    Toast.makeText(mContext, "socket connected", Toast.LENGTH_SHORT).show()
//                    val action = JSONObject()
//                    action.put("x",5000)
//                    action.put("y",700)
//                    action.put("action", "mouse-move")
//                    try {
//                        val outputStream = socket.outputStream
//                        if (outputStream != null) {
//                            Toast.makeText(mContext, "message.toByteArray()", Toast.LENGTH_SHORT).show()
//                            Toast.makeText(mContext, action.toString(), Toast.LENGTH_SHORT).show()
//                            outputStream.write(action.toString().toByteArray())
//                            Toast.makeText(mContext, "outputStream.write()", Toast.LENGTH_SHORT).show()
//                            outputStream.flush()
//                            Toast.makeText(mContext, "outputStream.flush()", Toast.LENGTH_SHORT).show()
//                            Toast.makeText(mContext, "Sent", Toast.LENGTH_SHORT).show()
//                        }
//                    } catch(e: Exception) {
//                        Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show()
//                    } finally {
//                        Toast.makeText(mContext, "CLOSIING.flush()", Toast.LENGTH_SHORT).show()
//                        socket.outputStream.close()
//                        socket.close()
//                    }
//
//                }
//                if(socket.isConnected()) {}
//                socket.close()
//                val mousePosition = JSONObject()
//                mousePosition.put("x",4000)
//                mousePosition.put("y",700)
//                val action = JSONObject()
//                action.put("action", "mouse-move")
//                BluetoothClient.setSocket(socket);
//                BluetoothClient(action.toString(), mContext).start()
//                try {
////                    val outputStream = socket.outputStream
////                    if (outputStream != null) {
////                        Toast.makeText(mContext, "message.toByteArray()", Toast.LENGTH_SHORT).show()
////                        Toast.makeText(mContext, action.toString(), Toast.LENGTH_SHORT).show()
////                        outputStream.write(action.toString().toByteArray())
////                        Toast.makeText(mContext, "outputStream.write()", Toast.LENGTH_SHORT).show()
////                        outputStream.flush()
////                        Toast.makeText(mContext, "outputStream.flush()", Toast.LENGTH_SHORT).show()
////                        Toast.makeText(mContext, "Sent", Toast.LENGTH_SHORT).show()
////                    }
//                } catch(e: Exception) {
//                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show()
//                } finally {
//                    Toast.makeText(mContext, "CLOSIING.flush()", Toast.LENGTH_SHORT).show()
////                    socket.inputStream.close()
////                    socket.outputStream.close()
//                    socket.close()
//                    //outputStream.close()
//                    //inputStream.close()
//                    // this.socket.close()
//                }
                //BluetoothClient(action.toString(), mContext).start()
            } catch (e: Exception) {
                Toast.makeText(mContext, "CONNECTION FAILED", Toast.LENGTH_SHORT).show()
                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var deviceListsLayout: ConstraintLayout

        init {
            name = itemView.findViewById(R.id.roomName)
            deviceListsLayout = itemView.findViewById(R.id.DeviceListLayout)
        }
    }

    fun clear() {
        deviceData!!.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
//    fun addAll(list: List<BluetoothDevice>?) {
    fun addAll(list: List<String>?) {
        deviceData!!.addAll(list!!)
        notifyDataSetChanged()
    }

//    fun add(device: BluetoothDevice) {
    fun add(device: String) {
        deviceData!!.add(device)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.deviceData.size
    }

}