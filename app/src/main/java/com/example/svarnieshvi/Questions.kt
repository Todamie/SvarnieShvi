package com.example.svarnieshvi

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Questions : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questions, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? StartActivity)?.setFragmentTitle("Поддержка")

        val quality = view.findViewById<TextView>(R.id.quality)
        val type = view.findViewById<TextView>(R.id.type)

        // Создание SpannableString с подчеркиванием
        val qualit = SpannableString("Как определить качество снимка")
        qualit.setSpan(UnderlineSpan(), 0, qualit.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Создание SpannableString с подчеркиванием
        val typ = SpannableString("Как определить тип пленки")
        typ.setSpan(UnderlineSpan(), 0, typ.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Установка подчеркнутого текста в TextView
        quality.text = qualit
        type.text = typ

    }



}