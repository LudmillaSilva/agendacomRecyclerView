package com.ludmilla.agenda

class Juridica (nomePessoaJuridica: String,
                celularPessoaJuridica: String,
            val emailPessoaJuridica: String):
                Pessoa(nomePessoaJuridica,
                celularPessoaJuridica) {
    override fun toString(): String {
        return "Nome: $nome\nTelefone: $celular\nE-mail: $emailPessoaJuridica\n"
    }
}