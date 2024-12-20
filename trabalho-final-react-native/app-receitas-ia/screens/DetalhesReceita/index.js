import { useEffect } from "react";
import { FlatList, ScrollView, StyleSheet, Text } from "react-native";

export default function DetalhesReceita({ route }) {

    const dadosReceita = route.params?.receita;

    useEffect(() => {
        // console.log("Detalhes da receita!");
        console.log(dadosReceita);
        // console.log(route.params.receita);
    }, []);

    return (
        <ScrollView style={ estilo.container }>
            <Text style={ estilo.estiloTexto }>Nome da receita: { dadosReceita.nome }</Text>
            <Text style={ estilo.estiloTexto }>Modo de preparo</Text>
            <Text>{ dadosReceita.modoDePreparo }</Text>
            <Text style={ estilo.estiloTexto }>Categoria: { dadosReceita.categoria }</Text>
            <Text style={ estilo.estiloTexto }>Ingredientes:</Text>
            <FlatList
                data={ dadosReceita.ingredientes }
                renderItem={ ({ item }) => {

                    return (
                        <Text style={ estilo.estiloIngrediente }>
                            { item }
                        </Text>
                    );
                } } />
        </ScrollView>
    );
}

const estilo = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20
    },
    estiloTexto: {
        color: "black",
        fontWeight: "bold",
        fontSize: 18,
        marginBottom: 10,
        marginTop: 10
    },
    estiloIngrediente: {
        color: 'orange',
        fontWeight: 'bold',
        fontSize: 20,
        backgroundColor: 'black',
        padding: 20,
        margin: 20,
        borderRadius: 20,
        textTransform: 'uppercase',
        textAlign: 'center'
    }
});