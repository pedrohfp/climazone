package com.accenture.archref.core.network

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val LAT = "lat"
const val LON = "lon"

class LocationInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val originalHttpUrl: HttpUrl = request.url

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return chain.proceed(request)
        }

        val locationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    addLatLonOnEndpoint(originalHttpUrl, location, request, chain)
                } ?: chain.proceed(request)
            }
            .addOnFailureListener {
                chain.proceed(request)
            }

        return chain.proceed(request)
    }

    private fun addLatLonOnEndpoint(
        originalHttpUrl: HttpUrl,
        location: Location,
        request: Request,
        chain: Interceptor.Chain
    ): Response {
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(LAT, location.latitude.toString())
            .addQueryParameter(LON, location.longitude.toString())
            .build()
        val newRequest: Request = request.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}
