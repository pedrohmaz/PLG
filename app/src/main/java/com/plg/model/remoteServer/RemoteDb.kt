package com.plg.model.remoteServer

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class RemoteDb() {

    private val instance = FirebaseFirestore.getInstance()

    fun getDocument(collection: String, document: String): Task<DocumentSnapshot>? {
        return try {
            instance.collection(collection).document(document).get()
        } catch (e: Exception) {null}
    }

    fun getCollection(collection: String): Task<QuerySnapshot>? {
        return try {
            instance.collection(collection).get()
        } catch (e: Exception) {null}
    }

    fun getElementByParam(collection: String, param: String, value: Any): Task<QuerySnapshot>? {
        return try {
            instance.collection(collection).whereEqualTo(param, value).get()
        } catch (e: Exception) {null}
    }

    fun getElementByParam(collection: String, param: String, value: Any, param2: String, value2: Any): Task<QuerySnapshot>? {
        return try {
            instance.collection(collection).whereEqualTo(param, value).whereEqualTo(param2, value2).get()
        } catch (e: Exception) {null}
    }

    fun setDocument(collection: String, document: String, model: Any): Task<Void>? {
        return try {
            instance.collection(collection).document(document).set(model)
        } catch (e: Exception) {null}
    }

    fun addDocument(collection: String, model: Any): Task<DocumentReference>? {
        return try {
            instance.collection(collection).add(model)
        } catch (e: Exception) {null}
    }

    fun removeDocument(collection: String, document: String): Task<Void> {
        return instance.collection(collection).document(document).delete()
    }



}