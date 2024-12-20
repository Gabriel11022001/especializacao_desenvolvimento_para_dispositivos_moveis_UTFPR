import { StyleSheet } from "react-native";

const estilo = StyleSheet.create({
    container: {
        backgroundColor: 'white',
        padding: 30,
        borderRadius: 20,
        margin: 20,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        width: '90%'
    },
    estiloTextoProgresso: {
        color: 'black',
        fontWeight: 'bold',
        fontSize: 16,
        marginStart: 10
    }
});

export default estilo;