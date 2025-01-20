package com.example.workout.common.storage

import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
class FirebaseStorageWrapper(
    private val firebaseDatabase: DatabaseReference
) : Storage {
    override fun getNullableValue(key: String) = flow {
        emit(null)
    }

    override fun getValue(key: String): Flow<String> = flow {
        emit(value = firebaseDatabase.child(key).get().result.value as String)
    }

    override suspend fun setValue(key: String, value: String) {
        firebaseDatabase.child(key).setValue(value)
    }


    //TODO("")
    /*
    // Read from the database
myRef.addValueEventListener(object: ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        val value = snapshot.getValue<String>()
        Log.d(TAG, "Value is: " + value)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.w(TAG, "Failed to read value.", error.toException())
    }

})
     */
}

 */

