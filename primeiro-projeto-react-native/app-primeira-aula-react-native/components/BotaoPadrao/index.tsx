import { StyleSheet, Text, TouchableOpacity } from "react-native";

export default function BotaoPadrao(props) {

    return (
        <TouchableOpacity
        onPress={ props.onPressionarBotao }
        style={ estilo.estiloBtn }
        >
            <Text style={ estilo.estiloTexto }>{ props.tituloBotao }</Text>
        </TouchableOpacity>
    );
}

const estilo = StyleSheet.create({
    estiloBtn: {
        backgroundColor: 'purple',
        padding: 18,
        width: '90%',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: 16,
        margin: 16
    },
    estiloTexto: {
        color: 'white',
        textTransform: 'uppercase',
        fontSize: 18,
        fontWeight: 'bold'
    }
})