package com.example.svarnieshvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DocsViewModel: ViewModel() {

    var template_chosen = MutableLiveData<String>()
    var click_number = MutableLiveData<Int>()




}