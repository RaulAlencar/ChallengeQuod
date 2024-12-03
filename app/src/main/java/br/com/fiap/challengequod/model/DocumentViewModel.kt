package br.com.fiap.challengequod.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DocumentViewModel : ViewModel() {
    private val _documentCaptured = MutableStateFlow(false)
    val documentCaptured: StateFlow<Boolean> = _documentCaptured

    private val _documentValid = MutableStateFlow(false)
    val documentValid: StateFlow<Boolean> = _documentValid

    private val _biometricCaptured = MutableStateFlow(false)
    val biometricCaptured: StateFlow<Boolean> = _biometricCaptured

    private val _biometricValid = MutableStateFlow(false)
    val biometricValid: StateFlow<Boolean> = _biometricValid

    fun updateDocumentStatus(captured: Boolean, valid: Boolean) {
        _documentCaptured.value = captured
        _documentValid.value = valid
    }

    fun updateBiometricStatus(captured: Boolean, valid: Boolean) {
        _biometricCaptured.value = captured
        _biometricValid.value = valid
    }
}
