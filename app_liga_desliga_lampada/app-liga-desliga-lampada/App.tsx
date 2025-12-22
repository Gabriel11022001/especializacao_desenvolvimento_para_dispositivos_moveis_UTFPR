import { useState } from "react";
import { Image, ScrollView, StyleSheet, Text, TouchableOpacity } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

enum StatusLed {

  ligado,
  desligado

}

// coloquei um ip fake pois não tenho um arduino de verdade, só para exemplificar
const IP_ARDUINO = "192.168.0.121";

export default function App() {

  const [ statusLed, setStatusLed ] = useState<StatusLed>(StatusLed.desligado);

  const acionarLed = async () => {

    try {

      if (statusLed === StatusLed.ligado) {
        // desligar
        setStatusLed(StatusLed.desligado);

        await fetch(`http://${ IP_ARDUINO }/desligar`);
      } else {
        // ligar
        setStatusLed(StatusLed.ligado);

        await fetch(`http://${ IP_ARDUINO }/ligar`);
      }

      console.log("Led acionado com sucesso!");
    } catch (e) {
      console.error("Erro ao tentar-se acionar o led: " + e);
    }

  }

  return (
    <SafeAreaView style={ { flex: 1, padding: 20, backgroundColor: "#323232", alignItems: "center" } }>
      <ScrollView style={ { flex: 1 } }>
        <Image style={  styles.imgLed } source={ statusLed === StatusLed.ligado ? require("./assets/images/led_ligado.png")
          : require("./assets/images/led_desligado.png")
         } />
         <TouchableOpacity 
          style={ styles.btnLed }
          onPress={ acionarLed } >
          <Text style={ styles.txtBtnLed }>{ statusLed === StatusLed.ligado ? "Desligar" : "Ligar" }</Text>
         </TouchableOpacity>
      </ScrollView>
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  imgLed: {
    width: 300,
    height: 300,
    resizeMode: "contain",
    marginTop: 50
  },
  btnLed: {
    backgroundColor: "#fff",
    padding: 20,
    borderRadius: 20,
    marginTop: 100,
    width: "100%",
    alignItems: "center",
    justifyContent: "center"
  },
  txtBtnLed: {
    color: "#000",
    fontSize: 20,
    fontWeight: "bold",
    textAlign: "center"
  }
});