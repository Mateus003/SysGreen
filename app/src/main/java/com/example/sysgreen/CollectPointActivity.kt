package com.example.sysgreen

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.sysgreen.databinding.ActivityCollectPointBinding

class CollectPointActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityCollectPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.background_button_return) // ícone da seta branca
            setTitle("")
            setHomeAsUpIndicator(R.drawable.ic_arrow_back) // ícone da seta branca
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Add a marker in Recife and move the camera
        val cinUfpe = LatLng(-8.0554591, -34.953518)
        mMap.addMarker(MarkerOptions().position(cinUfpe).title("Marker in CinUfpe"))

        val zoomLevel = 15.0 //nível de zoom desejado
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cinUfpe, zoomLevel.toFloat()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // finaliza a Activity atual
                startActivity(Intent(this, NavigationActivity::class.java)) // inicia a NavigationActivity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}