package br.com.fiap.challengequod.model

data class UserInfo(
    val cpf: String,
    val name: String,
    val phone: String,
    val cep: String,
    val street: String,
    val number: String,
    val complement: String,
    val neighborhood: String,
    val city: String,
    val state: String
)
