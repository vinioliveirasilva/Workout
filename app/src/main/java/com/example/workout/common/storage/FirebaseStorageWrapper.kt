package com.example.workout.common.storage

import com.google.firebase.database.DatabaseReference

class FirebaseStorageWrapper(
    private val firebaseDatabase: DatabaseReference
) : Storage {
    override suspend fun <T> getNullableValue(): T? {
        return null
    }

    override suspend fun <T> getValue(default: T): T {
       return Any() as T
    }

    override suspend fun <T> setValue(value: T) {
        firebaseDatabase.setValue(value)
    }

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

