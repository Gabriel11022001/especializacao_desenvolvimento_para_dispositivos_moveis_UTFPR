import BotaoPadrao from "@/components/BotaoPadrao";
import Cabecalho from "@/components/Cabecalho";
import { Button, Image, StyleSheet, Text, View } from "react-native";
import { Colors } from "react-native/Libraries/NewAppScreen";

export default function Index() {

  const testeEventoCliqueBotao = () => {
    console.log('Evento de clique no botão!')
  }

  return (
    <View style={ estilos.container }>
      <Cabecalho tituloApresentar="Meu primeiro componente" uppercase={ true } />
      <Image
        source={{
          uri: 'https://th.bing.com/th/id/OIP.v9PGKrnGptlo-ioJXgXccwHaEK?rs=1&pid=ImgDetMain'
        }}
        style={{
          width: 200,
          height: 200
        }}
      />
      <Image 
        source={ require('../assets/images/react-logo.png') }
      />
      <Text style={ estilos.textoEstilo }>Primeiro projeto</Text>
      <Button
        title="Meu primeiro botão"
        /*onPress={ () => {
          console.log('Evento de clique no botão!')
        }}*/
       onPress={ testeEventoCliqueBotao }
      />
      <BotaoPadrao
        tituloBotao="Clique aqui"
        onPressionarBotao={ () => {
          console.log('Clicou no super botão')
        } } />
    </View>
  );
}

const estilos = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'yellow'
  },
  textoEstilo: {
    color: 'red',
    fontWeight: 'bold',
    fontSize: 20,
    textAlign: 'center'
  }
});