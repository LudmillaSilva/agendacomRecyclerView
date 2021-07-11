package com.ludmilla.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContatosAdapter(var dataSet: MutableList<Pessoa>): RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder>() {

    

     class ContatosViewHolder(view:View):RecyclerView.ViewHolder(view){
        val itemnome= view.findViewById<TextView>(R.id.itemNome)
        val itemtelefone= view.findViewById<TextView>(R.id.itemTelefone)
        val itemreferencia= view.findViewById<TextView>(R.id.itemReferencia)
        val itememail=view.findViewById<TextView>(R.id.itemEmail)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        val viewInstance = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return (ContatosViewHolder(viewInstance))
    }

    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        holder.itemnome.text= dataSet[position].nome
        holder.itemtelefone.text= dataSet[position].celular
        if(dataSet[position] is Fisica){
            val pessoa: Fisica = dataSet[position] as Fisica
            holder.itemreferencia.text= pessoa.referencia
        }
        else{
            val pessoaJ: Juridica = dataSet[position] as Juridica
            holder.itememail.text=pessoaJ.emailPessoaJuridica
        }
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }

    fun dataSetEscolha(newDataSet: MutableList<Pessoa>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }


}