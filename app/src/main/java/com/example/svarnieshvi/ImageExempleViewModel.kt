package com.example.svarnieshvi

import android.media.Image
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageExempleViewModel : ViewModel() {
    val selectedImageUri = MutableLiveData<Uri>()
    /*val selectedImageUri: LiveData<Uri>
        get() = _selectedImageUri*/

    fun setSelectedImageUri(uri: Uri) {
        selectedImageUri.value = uri
    }
}
