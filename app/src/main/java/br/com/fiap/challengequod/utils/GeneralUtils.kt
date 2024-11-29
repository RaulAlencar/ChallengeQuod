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
    // Remover qualquer caractere não numérico (como ponto ou hífen)
    val cpfNumerico = cpf.replace(Regex("[^0-9]"), "")

    // Verificar se o CPF tem 11 dígitos
    if (cpfNumerico.length != 11) {
        return -1 // Retorna -1 caso o CPF não tenha o formato correto
    }

    // Calcular a soma dos dígitos
    val somaDosDigitos = cpfNumerico.sumOf { it.toString().toInt() }

    // Aplicar uma fórmula simples para gerar o score (isso é apenas uma simulação simples)
    var score = somaDosDigitos * 17 % 1001 // Multiplia por 17 e usa o módulo 1001 para garantir o intervalo

    // Garantir que o score final fique entre 1 e 1000
    if (score == 0) score = 1 // Se o score for 0, retorna 1 (para evitar zero como score)

    return score
}

// Função de validação do CPF
fun isValidCPF(cpf: String): Boolean {
    return cpf.all { it.isDigit() } && cpf.length == 11
}

