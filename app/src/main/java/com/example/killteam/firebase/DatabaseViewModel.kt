package com.example.killteam.firebase

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DatabaseViewModel(): ViewModel()
{

    var data by mutableStateOf<GameInfoList?>(null)
        private set

    val db = FirebaseFirestore.getInstance()

    fun SaveNewGame(gameData: GameInfo, user : UserData?)
    {
        if(user == null)
        {
            return;
        }
        viewModelScope.launch {
            try
            {
                val document = db.collection("users").document(user.userId).get().await()

                // 2. Przygotuj zaktualizowane dane
                val currentList = document.toObject(GameInfoList::class.java)?.games ?: emptyList()
                val updatedData = GameInfoList(
                    username = user.username ?: "Player",
                    games = currentList + gameData
                )

                db.collection("users").document(user.userId).set(updatedData).addOnSuccessListener {
                    Log.d("Firestore", "User data successfully written!")
                }.addOnFailureListener { e ->
                    Log.w("Firestore", "Error writing document", e)
                }

            } catch (e: Exception) {
                Log.w("Firestore", "Saving Data Failed (loading upToDate data)", e)
                throw e
            }
        }
    }

    fun getUserData(user: UserData?)
    {
        if(user == null)
        {
            return;
        }
        db.collection("users").document(user.userId)
            .get()
            .addOnSuccessListener { document ->
                // Użyj viewModelScope aby zapewnić główny wątek
                viewModelScope.launch {
                    data = document.toObject(GameInfoList::class.java)
                    Log.d("Firestore", "Data loaded: ${data?.username}")
                }
            }
            .addOnFailureListener { e ->
                viewModelScope.launch {
                    data = null
                    Log.w("Firestore", "Load error", e)
                }
            }
    }

    fun getDataByIndex(index: Int) : GameInfo
    {
        return data?.games[index] ?: GameInfo()
    }
}