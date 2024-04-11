package com.example.midterm_project.presentation.event.registration

sealed class RegistrationEvent {
    data class Register(val email: String, val password: String, val passwordCheck: String) :
        RegistrationEvent()

    object ResetErrorMessage : RegistrationEvent()
}
