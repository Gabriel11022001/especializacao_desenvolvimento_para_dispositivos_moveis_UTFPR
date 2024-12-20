import { Image, Text, TouchableOpacity } from "react-native";
import estilo from "./estilo";
import { useEffect } from "react";

export default function ItemListaReceita({
    receita, // objeto representando a receita
    onVisualizarReceita // função para o usuário ser redirecionado para a tela com detalhes da receita
}) {

    useEffect(() => {
        console.log("Apresentar receita!");
        console.log(receita);
    }), [];

    return (
        <TouchableOpacity
            style={ estilo.cardReceita }
            onPress={ () => {
                console.log("Visualizar a receita " + receita.nome);
                onVisualizarReceita(receita);
            } } >
                <Text style={ estilo.estiloTextoNomeReceita }>{ receita.nome }</Text>
                <Text>Categoria: { receita.categoria }</Text>
        </TouchableOpacity>
    );
}