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
        val zoomLevel = 11.5 //nível de zoom desejado

        // Add a marker in Recife and move the camera
        val cinUfpe = LatLng(-8.0554591, -34.953518)
        mMap.addMarker(MarkerOptions().position(cinUfpe).title("Ponto de coleta 1"))


        val portoDigital = LatLng(-8.0629122, -34.8753243)
        mMap.addMarker(MarkerOptions().position(portoDigital).title("Ponto de coleta 2"))

        val varzea = LatLng(-8.0629102 ,-34.9139493)
        mMap.addMarker(MarkerOptions().position(varzea).title("Ponto de coleta 3"))

        val goiana = LatLng(-7.5628073, -35.0187012)
        mMap.addMarker(MarkerOptions().position(goiana).title("Ponto de coleta 4"))

        val paulista = LatLng(-7.9403447, -34.8875948)
        mMap.addMarker(MarkerOptions().position(paulista).title("Ponto de coleta 5"))

        val tacaruna = LatLng(-8.0379085, -34.874460)
        mMap.addMarker(MarkerOptions().position(tacaruna).title("Ponto de coleta 6"))

        val rioMar = LatLng(-8.086054, -34.9709663)
        mMap.addMarker(MarkerOptions().position(rioMar).title("Ponto de coleta 7"))

        val patteo = LatLng(-7.9938732, -34.8431548)
        mMap.addMarker(MarkerOptions().position(patteo).title("Ponto de coleta 8"))

        val abreuELima = LatLng(-7.9065336, -34.905088)
        mMap.addMarker(MarkerOptions().position(abreuELima).title("Ponto de coleta 9"))

        val igarassu= LatLng(-7.8322066, -34.9077835)
        mMap.addMarker(MarkerOptions().position(igarassu).title("Ponto de coleta 10"))

        val ufrpe = LatLng(-8.0175579, -34.9540463)
        mMap.addMarker(MarkerOptions().position(ufrpe).title("Ponto de coleta 11"))

        val upe = LatLng(-8.0164588, -34.9888457)
        mMap.addMarker(MarkerOptions().position(upe).title("Ponto de coleta 12"))

        val parqueJaqueira= LatLng(-8.0370774, -34.9074205)
        mMap.addMarker(MarkerOptions().position(parqueJaqueira).title("Ponto de coleta 13"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parqueJaqueira, zoomLevel.toFloat()))




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