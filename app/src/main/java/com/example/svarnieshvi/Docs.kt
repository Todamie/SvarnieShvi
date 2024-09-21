package com.example.svarnieshvi

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class Docs : Fragment() {

    private lateinit var vm: DocsViewModel

    //var template_chosen:String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_docs, container, false)
        if (savedInstanceState != null) {
            //были сохраненные состояния, надо восстановить
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? StartActivity)?.setFragmentTitle("Создание документа")

        vm = ViewModelProvider(this).get(DocsViewModel::class.java)

        var view2: View = layoutInflater.inflate(R.layout.fragment_docs_template, null)

        if (vm.template_chosen.value.isNullOrEmpty()){
            spinner_enable(view2)
        }

        var template_chosen: TextView = view.findViewById(R.id.template_chosen)
        vm.template_chosen.observe(viewLifecycleOwner, Observer { template_chosen.text = it })


        val clicker:Button = view.findViewById(R.id.clicker)
        vm.click_number.value = 0
        vm.click_number.observe(viewLifecycleOwner, Observer { clicker.text = it.toString() })
        clicker.setOnClickListener{
            vm.click_number.value = vm.click_number.value?.plus(1)
        }

    }


    fun spinner_enable(view2:View) {
        val spinner: Spinner = view2.findViewById(R.id.spin_template)
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.template,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                val choose = resources.getStringArray(R.array.template)
                var choose_item = choose[selectedItemPosition]
                vm.template_chosen.value = choose_item
                val toast = Toast.makeText(
                    requireContext(),
                    "Ваш выбор: " + vm.template_chosen.value.toString(), Toast.LENGTH_SHORT
                )
                toast.show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        val builder = AlertDialog.Builder(context)

        builder.setView(view2)
        val alertDialog = builder.create()
        alertDialog.show()
    }


}