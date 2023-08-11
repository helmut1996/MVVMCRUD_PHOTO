package com.helcode.fotosqlite

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.helcode.fotosqlite.model.User
import com.helcode.fotosqlite.viewmodel.UserViewModel
import java.io.ByteArrayOutputStream

lateinit var imagen:ImageView
lateinit var btn_foto:FloatingActionButton
lateinit var btn_save:Button
lateinit var text_name:TextView
lateinit var text_lastname:TextView
val REQUEST_CODE_TAKE_PHOTO=1;
val stream = ByteArrayOutputStream()

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel= ViewModelProvider(this)[UserViewModel::class.java]


        imagen = findViewById(R.id.imageView)
        btn_foto = findViewById(R.id.btn_camara)
        btn_save = findViewById(R.id.Guardar)
        text_name= findViewById(R.id.edit_name)
        text_lastname= findViewById(R.id.text_apellido)

        btn_foto.setOnClickListener {
            stream.reset()
            takePhoto()
        }

        btn_save.setOnClickListener {
            SaveData()
            text_name.text = ""
            text_lastname.text= ""
            imagen.setImageDrawable(R.drawable.baseline_image_24.toDrawable())
        }
    }

     fun imageToBitmap(image: Bitmap): ByteArray
    {
        stream.reset()
        image.compress(Bitmap.CompressFormat.PNG, 90, stream)


        Log.d("Conversion",stream.toByteArray().toString())
        return stream.toByteArray()
    }


    private fun SaveData() {
        val Name= text_name.text.toString().trim()
        val Lasname= text_lastname.text.toString().trim()

        if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Lasname)){
            userViewModel.insert(this,User(name = Name, lastname = Lasname, photo = stream.toByteArray()))
        }else{
            Toast.makeText(applicationContext,"debe llena los campos",Toast.LENGTH_SHORT).show()
        }
    }


    fun takePhoto(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePicture->
            takePicture.resolveActivity(packageManager)?.also {
                startActivityForResult(takePicture, REQUEST_CODE_TAKE_PHOTO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imagen.setImageBitmap(imageBitmap)

            imageToBitmap(imageBitmap)

        }
    }
}