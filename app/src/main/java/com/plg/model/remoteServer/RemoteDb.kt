package com.plg.model.remoteServer

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class RemoteDb() {

    private val instance = FirebaseFirestore.getInstance()

    fun getDocument(collection: String, document: String): Task<DocumentSnapshot> {
        return instance.collection(collection).document(document).get()
    }

    fun getCollection(collection: String): Task<QuerySnapshot> {
        return instance.collection(collection).get()
    }

    fun saveDocument(collection: String, document: String, model: Any): Task<Void> {
        return instance.collection(collection).document(document).set(model)
    }

    fun getElementByParam(collection: String, param: String, value: Any): Task<QuerySnapshot> {
        return instance.collection(collection).whereEqualTo(param, value).get()
    }

}