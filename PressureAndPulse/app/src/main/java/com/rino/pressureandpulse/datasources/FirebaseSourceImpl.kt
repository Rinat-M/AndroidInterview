package com.rino.pressureandpulse.datasources

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.rino.pressureandpulse.entities.Measurement
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseSourceImpl : MeasurementsSource {

    companion object {
        private const val MEASUREMENTS_COLLECTION = "Measurements"
        private const val TAG = "FirebaseSourceImpl"
    }

    private val store = Firebase.firestore
    private val collection = store.collection(MEASUREMENTS_COLLECTION)

    override fun getMeasurements(): List<Measurement> {
        TODO("Not yet implemented")
    }

    override fun getMeasurementsFlow(): Flow<List<Measurement>> = callbackFlow {
        val subscription = collection.addSnapshotListener { snapshot, _ ->
            if (snapshot == null) {
                return@addSnapshotListener
            }

            try {
                val measurements = snapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject<Measurement>()
                }
                trySend(measurements)
            } catch (e: Throwable) {
                Log.e(TAG, "Detect an error in a method getMeasurementsFlow()", e)
            }
        }

        awaitClose { subscription.remove() }
    }

    override fun deleteMeasurement(id: String) {
        collection.document(id).delete()
    }

    override fun addMeasurement(measurement: Measurement) {
        collection.add(measurement.toMap())
    }

    override fun updateMeasurement(measurement: Measurement) {
        collection.document(measurement.id).set(measurement.toMap())
    }

}