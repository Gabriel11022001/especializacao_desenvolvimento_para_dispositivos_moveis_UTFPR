import { StyleSheet } from "react-native";

const estilo = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center'
    },
    txtTitulo: {
        color: 'black',
        fontWeight: 'bold',
        fontSize: 20,
        marginTop: 30
    },
    txtInformeIngredientes: {
        color: 'black',
        textAlign: 'center',
        margin: 30,
        fontSize: 16
    },
    estiloCampoIngredientes: {
        width: '95%',
        padding: 10,
        height: 100,
        backgroundColor: 'white',
        borderRadius: 20,
        marginTop: 30,
        borderColor: 'rgba(189, 195, 199, 1.0)',
        borderWidth: 1,
        fontSize: 18
    }
});

export default estilo;