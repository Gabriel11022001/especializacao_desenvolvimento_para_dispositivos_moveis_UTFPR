import { StyleSheet } from "react-native";

const estilo = StyleSheet.create({
    cardReceita: {
        width: '90%',
        marginStart: '5%',
        marginEnd: '5%',
        backgroundColor: 'white',
        borderRadius: 20,
        padding: 20,
        marginTop: 20,
        marginBottom: 10
    },
    estiloTextoNomeReceita: {
        color: 'orange',
        fontSize: 20,
        fontWeight: 'bold',
        textTransform: 'uppercase'
    },
    estiloFotoReceita: {
        width: '100%',
        marginBottom: 10,
        heigh: 100
    }
});

export default estilo;