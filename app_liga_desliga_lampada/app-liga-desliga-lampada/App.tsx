import { useState } from "react";
import { Alert, Image, ScrollView, StyleSheet, Text, TouchableOpacity } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

enum StatusLed {

  ligado,
  desligado

}

export default function App() {

  const [ statusLed, setStatusLed ] = useState<StatusLed>(StatusLed.desligado);

  const acionarLed = async () => {

    try {

      const ipEsp32: string = "";

      if (statusLed === StatusLed.ligado) {
        // desligar
        setStatusLed(StatusLed.desligado);

        await fetch(`http://${ ipEsp32 }/led/off"`);
      } else {
        // ligar
        setStatusLed(StatusLed.ligado);

        await fetch(`http://${ ipEsp32 }/led/on"`);
      }

      Alert.alert("Sucesso", "Led ligado com sucesso!", [
        {
          style: "default",
          text: "OK",
          onPress: () => {}
        }
      ]);
    } catch (e) {
      Alert.alert("Atenção!", "Erro ao tentar-se ligar o led, tente novamente: " + e, [
        {
          style: "cancel",
          text: "OK",
          onPress: () => {}
        }
      ]);
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