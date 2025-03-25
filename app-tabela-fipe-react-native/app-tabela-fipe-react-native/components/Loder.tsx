import cores from "@/assets/colors/colors"
import { StyleSheet, Text, View } from "react-native"

export default function Loader() {

    return (
        <View style={estilo.containerLoader}>
            <Text style={estilo.textoLoader}>Carregando, aguarde...</Text>
        </View>
    )
}

const estilo = StyleSheet.create({
    containerLoader: {
        flex: 1,
        backgroundColor: cores.fundo,
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'column'
    },
    textoLoader: {
        color: 'black',
        textAlign: 'center',
        fontWeight: 'bold',
        fontSize: 20
    }
})