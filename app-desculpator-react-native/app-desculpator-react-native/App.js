import { useEffect, useState } from "react";
import { ScrollView, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";
import { geradorDeDesculpas } from "./services/gerador_respostas";

export default function App() {

  const [ desculpa, setDesculpa ] = useState('');
  const [ respostaDesculpa, setRespostaDesculpa ] = useState('');
  const [ carregando, setCarregando ] = useState(false)

  /**
   * função para fazer requisição para a api
   * da IA para obter a resposta e apresentar na tela
   * para o usuário
   */
  const gerarDesculpas = async () => {
    setCarregando(true);

    try {
      
      if (desculpa.length < 5) {
        alert("A desculpa precisa ter no mínimo 5 caraceteres!");
      } else {
        const resp = await geradorDeDesculpas(desculpa);
        console.log("Resposta: " + resp);
        setRespostaDesculpa(resp);
      }

    } catch (e) {
      alert("Erro ao tentar-se gerar a desculpa: " + e);
    } finally {
      setCarregando(false);
    }

  }

  const apresentarRespostaDesculpa = () => {

    if (respostaDesculpa == "") {

      return <Text style={ estilos.estiloRespostaDesculpaNaoInformado }>
        Resposta da sua desculpa...
      </Text>
    }

    return <Text style={ estilos.estiloRespostaDesculpaInformada }>
      { respostaDesculpa }
    </Text>
  }

  useEffect(() => {
    console.log('Desculpa digitada: ' + desculpa)
  }, [ desculpa ]);

  return (
    <ScrollView style={ estilos.container }>

      <View style={ estilos.containerView }>

        <Text style={ estilos.estiloTitulo }>Desculpator</Text>

        <TextInput 
          style={ estilos.estiloCampo }
          placeholder="Informe a desculpa..."
          inputMode="text"
          value={ desculpa }
          onChangeText={ (desculpaDigitada) => setDesculpa(desculpaDigitada) } />

        <TouchableOpacity
        onPress={ gerarDesculpas }
          style={ estilos.btnGerarDesculpas }>

          <Text style={ estilos.estiloTextoBtnGerarDesculpas } >
            { carregando ? "Carregando..." : "Gerar desculpa" }
          </Text>

        </TouchableOpacity>

        { /** área contendo a resposta */ }
        <View style={ estilos.containerRespostaDesculpa }>
          { apresentarRespostaDesculpa() }
        </View>

      </View>

    </ScrollView>
  );
}

const estilos = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fafafa'
  },
  containerView: {
    alignItems: 'center'
  },
  estiloTitulo: {
    color: 'red',
    fontWeight: 'bold',
    fontSize: 20,
    marginTop: 100,
    marginBottom: 20,
    textAlign: 'center'
  },
  estiloCampo: {
    width: '90%',
    backgroundColor: 'white',
    padding: 20,
    fontSize: 20,
    fontWeight: 'bold',
    color: 'red',
    borderColor: 'rgba(189, 195, 199, 1.0)',
    borderWidth: 1,
    borderRadius: 20
  },
  btnGerarDesculpas: {
    width: '90%',
    backgroundColor: 'red',
    padding: 20,
    borderRadius: 20,
    marginTop: 30
  },
  estiloTextoBtnGerarDesculpas: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 20,
    textTransform: 'uppercase'
  },
  estiloRespostaDesculpaNaoInformado: {
    color: 'black',
    fontWeight: 'bold'
  },
  estiloRespostaDesculpaInformada: {
    color: 'red',
    fontWeight: 'bold'
  },
  containerRespostaDesculpa: {
    width: '90%',
    marginTop: 30,
    padding: 20,
    borderRadius: 20,
    borderColor: 'red',
    borderWidth: 2,
    backgroundColor: '#fff'
  }
});