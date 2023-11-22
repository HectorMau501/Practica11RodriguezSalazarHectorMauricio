package com.example.prctica11rodriguezsalazarhectormauricio

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.postDelayed
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GaleriaFragment : Fragment() {

     private lateinit var vista: View
     private lateinit var canciones: Spinner
     private var cancionesSel: String = "Hey Jude"
     private var song1: MediaPlayer? = null
     private var song2: MediaPlayer? = null
     private var song3: MediaPlayer? = null
    private var songActual: MediaPlayer? = null
    private var reproduciendoActual = false

    private lateinit var barraTime: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_galeria, container, false)

        canciones = vista.findViewById(R.id.spnCancion)
        song1 = MediaPlayer.create(context, R.raw.thebeatlesheyjude)
        song2 = MediaPlayer.create(context, R.raw.devuelvemeamichica)
        song3 = MediaPlayer.create(context, R.raw.billiejean)

        barraTime = vista.findViewById(R.id.sbAudio)

        song1?.isLooping = true
        song2?.isLooping = true
        song3?.isLooping = true

        val lstCancion = resources.getStringArray(R.array.Canciones)
        val adapterSpn = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,lstCancion)
        canciones.adapter = adapterSpn
        canciones.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                cancionesSel = lstCancion[p2]
                when(cancionesSel){
                    "Hey Jude" -> reproducirSong(song1)
                    "Devuelveme a mi Chica" -> reproducirSong(song2)
                    "Billie Jean" -> reproducirSong(song3)

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val btnPausar = vista?.findViewById<Button>(R.id.btnPausar)
        btnPausar?.setOnClickListener { v ->
            pausar()
        }

        barraTime.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //Cambios en la posicion de la barra
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //Fin
                val nuevaPosicion = seekBar?.progress ?: 0
                songActual?.seekTo(nuevaPosicion)
            }
        })

        return vista
    }

    private fun reproducirSong(song: MediaPlayer?) {
        // pausar la cancion actual si está reproduciendose
        if (reproduciendoActual) {
            pausar()
        }

        if(!reproduciendoActual){
            val duracionTotal = song?.duration ?: 0
            barraTime.max = duracionTotal

            // iniciar la reproducción de la nueva cancion
            song?.start()
            songActual = song
            reproduciendoActual = true
            actualizarSeekBar()
        }
    }

    private fun pausar() {
        // pausar la cancion actual si está reproduciéndose
        songActual?.let {
            if (it.isPlaying) {
                it.pause()
                reproduciendoActual = false
            }
        }
    }

    fun actualizarSeekBar(){
        val handler = Handler()
        handler.postDelayed(object : Runnable{
            override fun run(){
                val posicionActual = songActual?.currentPosition ?: 0
                barraTime.progress = posicionActual

                //Programar la actualizacion en este caso 1000ms
                handler.postDelayed(this,1000)
            }
        }, 1000)
    }

}