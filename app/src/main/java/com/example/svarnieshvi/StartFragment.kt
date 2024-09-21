package com.example.svarnieshvi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class StartFragment : Fragment() {

    private var selectedImageFileName: String? = null
    private lateinit var vm: ImageExempleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? StartActivity)?.setFragmentTitle("Загрузка изображений")

        vm = ViewModelProvider(this).get(ImageExempleViewModel::class.java)

        val upload: Button = view.findViewById(R.id.upload)
        val btn_start: Button = view.findViewById(R.id.btn_start)
        val upload_files: TextView = view.findViewById(R.id.upload_files_name)
        val qualitySpinner: MaterialAutoCompleteTextView = view.findViewById(R.id.quality_spinner)
        val typeSpinner: MaterialAutoCompleteTextView = view.findViewById(R.id.type_spinner)

        // Initialize the dropdown menu
        val itemsQuality = listOf("6", "5", "4", "3", "2", "1")
        val adapterQuality = ArrayAdapter(requireContext(), R.layout.list_item, itemsQuality)
        qualitySpinner.setAdapter(adapterQuality)

        val itemsType = listOf("C1", "C2", "C3", "C4", "C5", "C6")
        val adapterType = ArrayAdapter(requireContext(), R.layout.list_item, itemsType)
        typeSpinner.setAdapter(adapterType)

        val pickImage =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    selectedImageFileName = getFileName(it)
                    selectedImageFileName?.let { fileName ->
                        upload_files.text = "Загруженный файл: $fileName"
                        vm.selectedImageUri.value = it // Сохраняем изображение в ViewModel
                        Log.e("log1", uri.toString())
                    }
                }
            }

        btn_start.setOnClickListener {
            val bundle = Bundle().apply {
                putString("image_uri", vm.selectedImageUri.value.toString())
            }
            val emptyLoadFragment = EmptyLoad().apply {
                arguments = bundle
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, emptyLoadFragment)
                .addToBackStack(null)
                .commit()
        }

        upload.setOnClickListener {
            pickImage.launch("image/*")
        }
    }

    private fun getFileName(uri: Uri): String? {
        var result: String? = null
        context?.contentResolver?.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                result = if (displayNameIndex != -1) {
                    cursor.getString(displayNameIndex)
                } else {
                    uri.lastPathSegment
                }
            }
        }
        return result
    }
}
