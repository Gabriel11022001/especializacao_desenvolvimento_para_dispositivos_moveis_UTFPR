import { useEffect, useState } from "react";
import { FlatList, StyleSheet } from "react-native";
import ItemListaReceita from "../../components/ItemListaReceita";

export default function Receitas({ route, navigation }) {

    const [ receitas, setReceitas ] = useState( route.params.receitas );

    useEffect(() => {
        console.log("Receitas:");
        console.log(receitas);
    }, []);

    return (
        <FlatList
            data={ receitas }
            renderItem={ ({ item }) => {

                return (
                    <ItemListaReceita
                        receita={ item }
                        onVisualizarReceita={ (receita) => {
                            // console.log("Receita visualizar!");
                            // console.log(receita);
                            // redirecionar para a tela contendo os dados da receita
                            navigation.navigate("DetalhesReceita", { receita: receita });
                        } } />
                );
            } }
            keyExtractor={ (receita) => receita.nome } />
    );
}