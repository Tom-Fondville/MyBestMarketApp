package fr.epsi.mybestmarketapp

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.journeyapps.barcodescanner.camera.CameraManager
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

// Non fonctionnel
/*class ScanActivity : AppCompatActivity(), SurfaceHolder.Callback, Camera.PreviewCallback {

    private val TAG = "ScanActivity"
    private val CAMERA_PERMISSION_CODE = 100
    private lateinit var cameraManager: CameraManager
    private lateinit var surfaceView: SurfaceView
    private lateinit var scanButton: Button
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        surfaceView = findViewById(R.id.camera_preview)
        scanButton = findViewById(R.id.scan_button)
        handler = Handler()

        cameraManager = CameraManager(this)

        surfaceView.holder.addCallback(this)

        scanButton.setOnClickListener {
            cameraManager.requestPreviewFrame(this)
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        } else {
            startCamera(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    private fun startCamera(holder: SurfaceHolder) {
        try {
            cameraManager.openDriver(holder)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to open camera: $e")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                surfaceView.holder?.let { startCamera(it) }
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onPreviewFrame(data: ByteArray, camera: Camera) {
        val handlerThread = HandlerThread("DecodeThread")
        handlerThread.start()

        val handler = Handler(handlerThread.looper)

        val source = PlanarYUVLuminanceSource(
            data,
            camera.parameters.previewSize.width,
            camera.parameters.previewSize.height,
            0,
            0,
            camera.parameters.previewSize.width,
            camera.parameters.previewSize.height,
            false
        )

        handler.post {
            val reader = MultiFormatReader()
            val bitmap = BinaryBitmap(HybridBinarizer(source))
            try {
                val result: Result = reader.decode(bitmap)
                handler.post { handleDecode(result) }
            } catch (e: Exception) {
                handler.post {
                    Log.e(TAG, "Failed to decode QR code: $e")
                    Toast.makeText(this, "Failed to scan QR code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun handleDecode(result: Result) {
        try {
            val json = JSONObject(result.text)
            val firstName = json.getString("firstName")
            val lastName = json.getString("lastName")
            val email = json.getString("email")
            val address = json.getString("address")
            val zipcode = json.getString("zipcode")
            val city = json.getString("city")
            val cardRef = json.getString("cardRef")

            val message = """
                First Name: $firstName
                Last Name: $lastName
                Email: $email
                Address: $address
                Zipcode: $zipcode
                City: $city
                Card Ref: $cardRef
            """.trimIndent()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Scan Result")
            builder.setCancelable(false)

            builder.setMessage(message)
                .setPositiveButton("OK") { dialogInterface, i ->
                    dialogInterface.dismiss()
                    cameraManager.requestPreviewFrame(this)
                }
            builder.create().show()
        } catch (e: JSONException) {
            Log.e(TAG, "Failed to parse JSON: $e")
            Toast.makeText(this, "Failed to scan QR code", Toast.LENGTH_SHORT).show()
            cameraManager.requestPreviewFrame(this)
        }
    }

    override fun onPause() {
        super.onPause()
        cameraManager.closeDriver()
    }

    override fun onResume() {
        super.onResume()
        surfaceView.holder?.let { startCamera(it) }
    }
}*/