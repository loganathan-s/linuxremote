package com.example.linuxcontrol

import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity


class ControlActivity : AppCompatActivity() {
//    var connectedDevice: BluetoothSocket = TODO()
    private var myVelocityTracker: VelocityTracker? = null
    private var startPosition: Point? = null
    private var endPosition: Point? = null
    private var rect: Rect? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
//        val device = intent.extras!!.getParcelable<BluetoothDevice>("device")
//        val uuids: Array<ParcelUuid> = device!!.getUuids()
        val mouseArea: View = findViewById(R.id.mouseView)
        mouseArea.setOnTouchListener(OnTouchListener { v, event ->
            val action = event.action
            rect =
                Rect(v.left, v.top, v.right, v.bottom)
            when (action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    if (rect!!.contains(
                            v.left + event.x.toInt(),
                            v.top + event.y.toInt()
                        )) {
                        Log.i("ENERED DOWN", ">>>>>>>>>>>>>>>>>>")
                        startPosition = Point(event.getX().toInt(), event.getY().toInt())
                    }

                }
//                MotionEvent.ACTION_UP -> {
//                    endPosition = Point(event.getX().toInt(), event.getY().toInt())
//                }
                MotionEvent.ACTION_MOVE -> {
                    Log.i("ENERED MOVVE", ">>>>>>>>>>>>>>>>>>")


                    if (rect!!.contains(
                            v.left + event.x.toInt(),
                            v.top + event.y.toInt()
                        )) {
                        endPosition = Point(event.getX().toInt(), event.getY().toInt())
                        var distance = Math.sqrt(Math.pow((startPosition!!.x-endPosition!!.x).toDouble(), 2.0) + Math.pow(
                            (startPosition!!.y-endPosition!!.y).toDouble(), 2.0
                        ))

//                        Log.i("XX", "xVelocity: $distance, xVelocity: ${startPosition!!.x}- ${endPosition!!.x}-")
//                        Log.i("YY", "YVelocity: $distance, xVelocity: ${startPosition!!.y}- ${endPosition!!.y}-")
                        obtainVelocityTracker(event)
                        val velocityTracker = myVelocityTracker!!
                        //Determine the pointer’s velocity//
                        velocityTracker.computeCurrentVelocity(1000)
                        //Retrieve the velocity for each pointer//
                        val xVelocity = myVelocityTracker!!.xVelocity
                        val yVelocity = myVelocityTracker!!.yVelocity
//                        Log the velocity in pixels per second//
                        Log.i("xVelocity", "xVelocity: $xVelocity, yVelocity: $yVelocity")
                        //Reset the velocity tracker to its initial state, ready to record the next gesture//
                        myVelocityTracker!!.clear()
                    }
                }
            }
            true
        })
        //mouseArea.onTouchEvent()
        try {
//            val connectedDevice: BluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(uuids[0].getUuid());
//            connectedDevice.connect()
//            if (connectedDevice.isConnected()) {
//                try {
//                    val outputStream = connectedDevice.outputStream
//                    Toast.makeText(applicationContext, "message.toByteArray()", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//                    outputStream.write(message.toByteArray())
//                    Toast.makeText(applicationContext, "outputStream.write()", Toast.LENGTH_SHORT).show()
//                    outputStream.flush()
//                    Toast.makeText(applicationContext, "outputStream.flush()", Toast.LENGTH_SHORT).show()
//                    Log.i("client", "Sent")
//                    Toast.makeText(applicationContext, "Sent", Toast.LENGTH_SHORT).show()
//                } catch (e: Exception) {
//
//                }
//            }
        } catch (e: Exception) {

        }

    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        obtainVelocityTracker(event)
//        when (event.action) {
//            MotionEvent.ACTION_MOVE -> {
//                val velocityTracker = myVelocityTracker!!
//                //Determine the pointer’s velocity//
//                velocityTracker.computeCurrentVelocity(1000)
//                //Retrieve the velocity for each pointer//
//                val xVelocity = myVelocityTracker!!.xVelocity
//                val yVelocity = myVelocityTracker!!.yVelocity
//                //Log the velocity in pixels per second//
//                Log.i("dsadsadsa", "xVelocity: $xVelocity, yVelocity: $yVelocity")
//                //Reset the velocity tracker to its initial state, ready to record the next gesture//
//                myVelocityTracker!!.clear()
//            }
//            else -> {
//            }
//        }
//        return true
//    }

    private fun obtainVelocityTracker(event: MotionEvent) {
        if (myVelocityTracker == null) { //Retrieve a new VelocityTracker object//
            myVelocityTracker = VelocityTracker.obtain()
        }
        myVelocityTracker!!.addMovement(event)
    }
}
