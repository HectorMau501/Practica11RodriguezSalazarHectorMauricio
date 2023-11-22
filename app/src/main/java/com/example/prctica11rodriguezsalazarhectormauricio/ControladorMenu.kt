package com.example.prctica11rodriguezsalazarhectormauricio

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException


class ControladorMenu(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PrincipalFragment()
            1 -> GaleriaFragment()
            2 -> FormularioFragment()
            else -> throw IllegalAccessException("Posicion Invalida")
        }
    }
}