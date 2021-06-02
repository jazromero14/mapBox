package com.korinver.maxbox

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager
import com.mapbox.mapboxsdk.style.layers.*
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val SOURCE_ID = "SOURCE_ID"
    private val ICON_ID = "ICON_ID"
    private  val LAYER_ID = "LAYER_ID"
    private var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_main)


        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)

        val customView = CustomPointer(this)
        val marker = MarkerView(LatLng(13.87,-89.63), customView)

        mapView?.getMapAsync { mapboxMap ->

            val markerViewManager = MarkerViewManager(mapView, mapboxMap)
            Log.e("MARKER", ""+marker.toString())
            marker.let {
                markerViewManager.addMarker(it)
            }
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {

                val lineLayer = LineLayer("line-layer", "line-source")

                // The layer properties for our line. This is where you can make the line dotted, set the color, etc.
                lineLayer.setProperties(
                    PropertyFactory.lineDasharray(arrayOf(0.01f, 2f)),
                    PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                    PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND),
                    PropertyFactory.lineWidth(5f),
                    PropertyFactory.lineColor(Color.parseColor("#e55e5e"))
                )
                it.addLayer(lineLayer)
// Map is set up and the style has loaded. Now you can add data or make other map adjustments
            }
            val latLngBounds = LatLngBounds.Builder()
                .include(LatLng(13.87, -89.63))
                .include(LatLng(13.69, -89.19))
                .build()
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10));
        }




    }


    override fun onMapReady(mapboxMap: MapboxMap) {

        mapboxMap.setStyle(Style.MAPBOX_STREETS) {


            // Create symbol manager object.
            val symbolManager = SymbolManager(mapView, mapboxMap,)

// Add click listeners if desired.
            symbolManager?.addClickListener { symbol ->

            }

            symbolManager?.addLongClickListener { symbol ->

            }

// Set non-data-driven properties.
            symbolManager?.iconAllowOverlap = true
            symbolManager?.iconTranslate = arrayOf(-4f, 5f)
            symbolManager?.iconRotationAlignment = ICON_ROTATION_ALIGNMENT_VIEWPORT
       }

//        val symbolLayerIconFeatureList: MutableList<Feature> = ArrayList()
//        symbolLayerIconFeatureList.add(
//            Feature.fromGeometry(
//                Point.fromLngLat(13.87, -89.63)
//            )
//        )
//        symbolLayerIconFeatureList.add(
//            Feature.fromGeometry(
//                Point.fromLngLat(-54.14164, -33.981818)
//            )
//        )
//        symbolLayerIconFeatureList.add(
//            Feature.fromGeometry(
//                Point.fromLngLat(-56.990533, -30.583266)
//            )
//        )
//
//        mapboxMap.setStyle(
//            Style.Builder()
//                .fromUri("mapbox://styles/jazromero142/ckpel0klw081l17s9wc5og9zz") // Add the SymbolLayer icon image to the map style
//                .withImage(
//                    ICON_ID, BitmapFactory.decodeResource(
//                        this@MainActivity.resources, R.drawable.ic_baseline_place
//                    )
//                )
//                .withSource(
//                    GeoJsonSource(
//                        SOURCE_ID,
//                        FeatureCollection.fromFeatures(symbolLayerIconFeatureList)
//                    )
//                )
//                .withLayer(
//                    SymbolLayer(LAYER_ID, SOURCE_ID)
//                        .withProperties(
//                            iconImage(ICON_ID),
//                            iconAllowOverlap(true),
//                            iconIgnorePlacement(true)
//                        )
//                )
//        ) {
//          // Map is set up and the style has loaded. Now you can add additional data or make other map adjustments.
//        }
    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }


}