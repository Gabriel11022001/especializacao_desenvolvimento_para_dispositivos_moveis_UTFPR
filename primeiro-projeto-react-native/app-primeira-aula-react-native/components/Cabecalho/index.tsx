import { StyleSheet, Text } from "react-native";

interface ITitulo {

    tituloApresentar: string,
    uppercase?: boolean

}

export default function Cabecalho( props: ITitulo ) {

    return (
        <Text style={[
            estilo.estiloTitulo,
            { textTransform: props.uppercase ? 'uppercase' : 'none' }
        ]}>
            { props.tituloApresentar }
        </Text>
    );
}

const estilo = StyleSheet.create({
    estiloTitulo: {
        fontSize: 16,
        color: 'red',
        fontWeight: 'bold'
    }
})