package com.example.prctica11rodriguezsalazarhectormauricio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.Toast


//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class FormularioFragment : Fragment() {

    private lateinit var vista: View
    private lateinit var estrellas: RatingBar

//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_formulario, container, false)

        estrellas = vista.findViewById(R.id.ratingBar)
        estrellas.rating = 3f

        estrellas.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if(estrellas.rating <= 2f){
                Toast.makeText(context, "Mal servicio",Toast.LENGTH_SHORT).show()
            } else if(estrellas.rating <= 4f){
                Toast.makeText(context, "Buen servicio",Toast.LENGTH_SHORT).show()
            }else if(estrellas.rating <= 5f){
                Toast.makeText(context, "Excelente  servicio",Toast.LENGTH_SHORT).show()
            }
        }

        return vista
    }

//    companion object {
//        fun newInstance(param1: String, param2: String) =
//            FormularioFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}