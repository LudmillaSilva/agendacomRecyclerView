package com.ludmilla.agenda

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var edtnome: EditText
    lateinit var celular: EditText
    lateinit var tipopessoa: RadioGroup
    lateinit var pessoafisica: RadioButton
    lateinit var pessoajuridica: RadioButton

    lateinit var cadastrar: Button
    lateinit var referencia: EditText
    lateinit var email: EditText
    lateinit var listagem: TextView

    //private var contatos: MutableList<Pessoa> = mutableListOf()

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtnome = findViewById(R.id.edtNome)
        celular = findViewById(R.id.edtCelular)
        celular.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        tipopessoa = findViewById(R.id.rdgTipoPessoa)
        pessoafisica = findViewById(R.id.rdPessoaFisica)
        pessoajuridica = findViewById(R.id.rdPessoaJuridica)
        referencia = findViewById(R.id.edtreferencia)
        email = findViewById(R.id.edtemail)
        cadastrar = findViewById(R.id.btnCadastrar)
        //listagem = findViewById(R.id.txtListagem)



        tipopessoa.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (pessoafisica.id == radio.id) {
                referencia.visibility = View.VISIBLE
                email.visibility = View.GONE
            } else {
                email.visibility = View.VISIBLE
                referencia.visibility = View.GONE
            }
        }

        cadastrar.setOnClickListener {
            var contatosexibidos: String = ""
            val nomeinserido = edtnome.text.toString()
            val celularinserido = celular.text.toString()
            if (nomeinserido.isNotEmpty() && celularinserido.isNotEmpty())
            {

                if (pessoafisica.isChecked) {
                    val referenciainserida = referencia.text.toString()
                    if(referenciainserida.isEmpty()){ referencia.error= getString(R.string.referenciaError) }
                    else {var pessoaFisica = Fisica(nomePessoaFisica = nomeinserido,
                        celularPessoaFisica = celularinserido,
                        referencia = referenciainserida)
                    contatos.add(pessoaFisica)
                    referencia.setText("")}

                } else {
                    val emailinserido = email.text.toString()
                    if(emailinserido.isEmpty()) {email.error = getString(R.string.emailError)} else{
                    var pessoaJuridica = Juridica(nomePessoaJuridica = nomeinserido,
                        celularPessoaJuridica = celularinserido,
                        emailPessoaJuridica = emailinserido)
                    contatos.add(pessoaJuridica)
                    email.setText("")}
                }
                edtnome.setText("")
                celular.setText("")
                Toast.makeText(this, getString(R.string.nomeSalvo, nomeinserido), Toast.LENGTH_SHORT).show()
            }
            else {
                edtnome.error = getString(R.string.nomeError)
                celular.error = getString(R.string.telefoneError)
            }

         //   listagem.text = ordenarContatos()

            val intent = Intent (this, SecondActivity::class.java)
            //intent.putExtra("contatos", ordenarContatos())

            startActivity(intent)

        }



    }


    private fun ordenarContatos(): String {
        var contatosexibidos1: String =""
        val listaOrdenada = contatos.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.nome })
        )

        for (x in listaOrdenada) {
            contatosexibidos1 += x.toString()


        }

        return contatosexibidos1
    }
    companion object{
        //val CAMERA_REQUEST_CODE = 14523
        //val NOME_KEY = "XUXAMENEGHEL"
        //val STUDENT_KEY = "ALUNA"
        val contatos =  mutableListOf<Pessoa>()
        fun ordenarContatos(): List<Pessoa> {
            var contatosexibidos1: String =""
            val listaOrdenada = contatos.sortedWith(
                compareBy(String.CASE_INSENSITIVE_ORDER, { it.nome })
            )

            for (x in listaOrdenada) {
                contatosexibidos1 += x.toString()


            }

            return listaOrdenada
        }
    }


}









