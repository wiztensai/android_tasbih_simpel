package com.example.tasbihsimpel

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var btnAdd:ImageButton
    lateinit var btnMin:ImageButton
    lateinit var btnRefresh:ImageButton
    lateinit var etTargetUcap:EditText
    lateinit var tvTotalUcap:TextView

    var totalUCap = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btnAdd)
        btnMin = findViewById(R.id.btnMin)
        btnRefresh = findViewById(R.id.btnRefresh)
        etTargetUcap = findViewById(R.id.etTargetUcap)
        tvTotalUcap = findViewById(R.id.tvTotalUcap)

        btnAdd.setOnClickListener {
            tambahUcap()
        }

        btnMin.setOnClickListener {
            kurangiTotalUcap()
        }

        btnRefresh.setOnClickListener {
            ulangMenghitung()
        }
    }

    fun tambahUcap() {
        totalUCap = totalUCap + 1
        tvTotalUcap.text = totalUCap.toString()

        var targetUcap = 0
        if (etTargetUcap.text.isEmpty()) {
            // do nothing
        } else {
            targetUcap = etTargetUcap.text.toString().toInt()
        }

        if (targetUcap == 0) return

        if (totalUCap >= targetUcap) {
            Toast.makeText(this,"Target ucap terpenuhi!", Toast.LENGTH_SHORT).show()

            ringtone()
            vibrate ()
        }
    }

    fun kurangiTotalUcap() {
        totalUCap = totalUCap - 1

        if (totalUCap < 0) {
            tvTotalUcap.text = "0"
        } else {
            tvTotalUcap.text = totalUCap.toString()
        }
    }

    fun ulangMenghitung() {
        totalUCap = 0
        tvTotalUcap.text = totalUCap.toString()
    }

    fun ringtone() {
        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun vibrate () {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(500)
        }
    }
}