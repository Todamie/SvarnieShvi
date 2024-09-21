package com.example.svarnieshvi

import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Result : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? StartActivity)?.setFragmentTitle("Результат анализа")

        val imageEx: ImageView = view.findViewById(R.id.imageEx)
        val defect = view.findViewById<TextView>(R.id.defect_underline)
        val status = view.findViewById<TextView>(R.id.defect_status_underline)

        val imageUri = arguments?.getString("image_uri")?.let { Uri.parse(it) }

        imageUri?.let {
            Glide.with(requireContext())
                .load(it)
                .into(imageEx)
        }



        // Создание SpannableString с подчеркиванием
        val defec = SpannableString("Поперечная трещина")
        defec.setSpan(UnderlineSpan(), 0, defec.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Создание SpannableString с подчеркиванием
        val stat = SpannableString("Не допустимый")
        stat.setSpan(UnderlineSpan(), 0, stat.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Установка подчеркнутого текста в TextView
        defect.text = defec
        status.text = stat
    }
}