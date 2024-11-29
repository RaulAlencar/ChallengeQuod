package br.com.fiap.challengequod.utils

import androidx.compose.runtime.MutableState
import br.com.fiap.challengequod.model.UserInfo

fun getSuccessData(): UserInfo {
    return UserInfo(
        cpf = "123.456.789-00",
        name = "Ana Silva",
        phone = "(11) 91234-5678",
        cep = "01000-000",
        street = "Rua das Flores",
        number = "123",
        complement = "Apto 101",
        neighborhood = "Centro",
        city = "São Paulo",
        state = "SP"
    )
}

fun getFailureData(): UserInfo {
    return UserInfo(
        cpf = "987.654.321-00",
        name = "Carlos Pereira",
        phone = "(21) 98765-4321",
        cep = "20000-000",
        street = "Avenida Brasil",
        number = "456",
        complement = "Casa",
        neighborhood = "Botafogo",
        city = "Rio de Janeiro",
        state = "RJ"
    )
}

fun getResetData(): UserInfo {
    return UserInfo(
        cpf = "",
        name = "",
        phone = "",
        cep = "",
        street = "",
        number = "",
        complement = "",
        neighborhood = "",
        city = "",
        state = ""
    )
}

fun calcularScore(cpf: String): Int {
    // Remover qualquer caractere não numérico
    val cpfNumerico = cpf.replace(Regex("[^0-9]"), "")

    // Verificar se o CPF tem pelo menos 9 dígitos
    if (cpfNumerico.length < 9) {
        return -1 // Retorna -1 caso o CPF não tenha formato suficiente
    }

    // Pegar os blocos de 3 dígitos e calcular a soma reduzida para um dígito
    val soma1 = reduzirParaUmDigito(cpfNumerico.substring(0, 3).sumOf { it.toString().toInt() })
    val soma2 = reduzirParaUmDigito(cpfNumerico.substring(3, 6).sumOf { it.toString().toInt() })
    val soma3 = reduzirParaUmDigito(cpfNumerico.substring(6, 9).sumOf { it.toString().toInt() })

    // Combinar as três somas em um número de 3 dígitos
    val score = "$soma1$soma2$soma3".toInt()

    return score
}

// Função auxiliar para somar os dígitos até restar apenas 1 dígito
fun reduzirParaUmDigito(soma: Int): Int {
    var resultado = soma
    while (resultado >= 10) {
        resultado = resultado.toString().sumOf { it.toString().toInt() }
    }
    return resultado
}


// Função de validação do CPF
fun isValidCPF(cpf: String): Boolean {
    return cpf.all { it.isDigit() } && cpf.length == 11
}

