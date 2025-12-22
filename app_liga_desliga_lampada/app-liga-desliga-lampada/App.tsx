import { useState } from "react";
import { Image, ScrollView, StyleSheet, Text, TouchableOpacity } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

enum StatusLampada {

  ligado,
  desligado

}

// coloquei um ip fake pois não tenho um arduino de verdade, só para exemplificar
const IP_ARDUINO = "192.168.0.121";

export default function App() {

  const [ statusLampada, setStatusLampada ] = useState<StatusLampada>(StatusLampada.desligado);

  const apresentarAlertaErro = (msg: string): void => {

  }

  const acionarLampada = async () => {

    try {

      if (statusLampada === StatusLampada.ligado) {
        // desligar
        setStatusLampada(StatusLampada.desligado);

        await fetch(`http://${ IP_ARDUINO }/desligar`);
      } else {
        // ligar
        setStatusLampada(StatusLampada.ligado);

        await fetch(`http://${ IP_ARDUINO }/ligar`);
      }

      console.log("Lâmpada acionada com sucesso!");
    } catch (e) {
      console.error("Erro ao tentar-se acionar a lâmpada: " + e);
      
      apresentarAlertaErro(`Erro ao tentar-se acionar a lâmpada: ${ e }`);
    }

  }

  return (
    <SafeAreaView style={ { flex: 1, padding: 20, backgroundColor: "#323232", alignItems: "center" } }>
      <ScrollView style={ { flex: 1 } }>
        <Image style={  styles.imgLampada } source={ statusLampada === StatusLampada.ligado ? require("./assets/images/led_ligado.png")
          : require("./assets/images/led_desligado.png")
         } />
         <TouchableOpacity 
          style={ styles.btnLampada }
          onPress={ acionarLampada } >
          <Text style={ styles.txtBtnLampada }>{ statusLampada === StatusLampada.ligado ? "Desligar" : "Ligar" }</Text>
         </TouchableOpacity>
      </ScrollView>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  imgLampada: {
    width: 300,
    height: 300,
    resizeMode: "contain",
    marginTop: 50
  },
  btnLampada: {
    backgroundColor: "#fff",
    padding: 20,
    borderRadius: 20,
    marginTop: 100,
    width: "100%",
    alignItems: "center",
    justifyContent: "center"
  },
  txtBtnLampada: {
    color: "#000",
    fontSize: 20,
    fontWeight: "bold",
    textAlign: "center"
  }
});