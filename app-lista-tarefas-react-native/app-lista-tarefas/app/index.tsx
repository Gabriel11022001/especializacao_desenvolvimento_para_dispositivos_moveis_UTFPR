import ItemLista from "@/components/ItemLista";
import { useState } from "react";
import { ScrollView, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";

export default function Index() {
  
  const [ nomeTarefa, setNomeTarefa ] = useState('')
  const [ tarefas, setTarefas ] = useState<Array<String>>([])

  const apresentarNomeTarefaDigitada = () => {
    console.log(nomeTarefa)
  }

  const adicionarItemLista = (tarefa: string) => {
    const novoItem: string = tarefa
    var itemsAdicionadosAteOMomento: Array<String> = tarefas

    for (var contador = 0; contador < itemsAdicionadosAteOMomento.length; contador++) {
      console.log(itemsAdicionadosAteOMomento[contador])
    }

    itemsAdicionadosAteOMomento.push(novoItem)

    setTarefas(itemsAdicionadosAteOMomento)
  }

  function marcarTarefaComoConcluida(tarefa: String) {
    var novaListagemTarefas: Array<String> = []

    for (var contador = 0; contador < tarefas.length; contador++) {

      if (tarefas[contador] != tarefa) {
        novaListagemTarefas.push(tarefas[contador])
      }

    }

    setTarefas(novaListagemTarefas)
  }

  return (
    <ScrollView style={ estilo.container }>
      <Text style={ estilo.estilo_titulo }>Lista de tarefas</Text>
      <TextInput
        style={ estilo.estilo_campo_digitar_nome_tarefa }
        placeholder="Digite o nome da tarefa..."
        inputMode="text"
        value={ nomeTarefa }
        onChangeText={ (nomeDigitado) => {
          setNomeTarefa(nomeDigitado)
          // apresentarNomeTarefaDigitada()
        } } />
      <TouchableOpacity 
        style={ estilo.estilo_btn_adicionar_tarefa }
        onPress={ () => {
          // apresentarNomeTarefaDigitada()
          adicionarItemLista(nomeTarefa)
        } }>
        <Text style={ estilo.estilo_texto_btn_adicionar_tarefa }>Adicionar tarefa</Text>
      </TouchableOpacity>

      {
        tarefas.map((tarefa) => {

          return <ItemLista item_lista_nome={ tarefa } on_marcar_tarefa_como_concluida={ () => {
            // marcar a tarefa como concluída
            marcarTarefaComoConcluida(tarefa)
          } } />
        })
      }

    </ScrollView>
  );
}

const estilo = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'blue'
  },
  estilo_titulo: {
    color: 'white',
    textAlign: 'center',
    marginTop: 20,
    fontWeight: 'bold',
    fontSize: 20
  },
  estilo_btn_adicionar_tarefa: {
    padding: 20,
    backgroundColor: 'black',
    borderRadius: 20,
    margin: 16
  },
  estilo_texto_btn_adicionar_tarefa: {
    color: 'white',
    fontWeight: 'bold',
    textTransform: 'uppercase',
    textAlign: 'center'
  },
  estilo_campo_digitar_nome_tarefa: {
    backgroundColor: 'white',
    padding: 30,
    borderRadius: 20,
    margin: 16,
    fontSize: 16
  }
})