package com.example.svarnieshvi

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmptyLoad : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var timerText: TextView
    private lateinit var vm: ImageExempleViewModel
    private lateinit var imageEx: ImageView
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_empty_load, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? StartActivity)?.setFragmentTitle("")

        progressBar = view.findViewById(R.id.progress_bar)
        timerText = view.findViewById(R.id.timer_text)
        vm = ViewModelProvider(requireActivity()).get(ImageExempleViewModel::class.java)
        imageEx = view.findViewById(R.id.imageEx)

        val imageUri = arguments?.getString("image_uri")?.let { Uri.parse(it) }
        vm.selectedImageUri.value = imageUri

        Log.e("log2", vm.selectedImageUri.toString())
        /*vm.selectedImageUri.observe(viewLifecycleOwner, Observer { uri ->
            uri?.let {
                Glide.with(requireContext())
                    .load(uri)
                    .into(imageEx)
            }
        })*/

        startTimer()
    }

    private fun startTimer() {
        job = CoroutineScope(Dispatchers.Main).launch {
            progressBar.isIndeterminate = true
            for (i in 5 downTo 0) {
                //timerText.text = "Time remaining: $i seconds"
                delay(1000)
            }
            // After the timer finishes, navigate to ResultFragment
            val bundle = Bundle().apply {
                putString("image_uri", vm.selectedImageUri.value.toString())
            }
            val resultFragment = Result().apply {
                arguments = bundle
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
    }
}
