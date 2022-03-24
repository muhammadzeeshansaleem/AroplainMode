package com.zeeshan.aroplainmode

import android.os.Bundle
import android.util.Log
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.zeeshan.aroplainmode.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Addpoint()
        getData()

    }

    private fun Addpoint() {
//        val database = Firebase.database
//        val myRef = database.getReference("aro")
//
//        myRef.setValue("Hello, World!")


            val miner=AroModel("0")
            database = Firebase.database.reference
            database.child("aro").setValue(miner)




    }

    fun getData() {

        val ref = FirebaseDatabase.getInstance().getReference("aro").child("status")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.e("as", dataSnapshot.toString());
                // Get Post object and use the values to update the UI
              //  val post = dataSnapshot.getValue(AroModel::class.java)
                val value = dataSnapshot.getValue<String>()

                Log.e("aa", value.toString())

                if (value.toString().equals("1")){
                    binding.status.setText("Areoplain Mode On")
                    // check current state first

                    Settings.Global.putString(contentResolver, "airplane_mode_on", "1")

// To Read

// To Read
                    val result: String =
                        Settings.Global.getString(contentResolver, Settings.Global.AIRPLANE_MODE_ON)
                  //  Toast.makeText(this, "result:$result", Toast.LENGTH_SHORT).show()


                }

                else if (value.toString().equals("0")) {
                    binding.status.setText("Areoplain Mode Off")
                }


                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(postListener)


    }
}