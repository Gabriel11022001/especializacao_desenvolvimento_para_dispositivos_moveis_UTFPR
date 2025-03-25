import { Button, StyleSheet, Text, View } from "react-native";

export default function ItemLista(props) {

    return (
        <View style={ estilo.item_lista_container }>
            <Text>{ props.item_lista_nome }</Text>
            <Button title="Marcar como concluída" onPress={ props.on_marcar_tarefa_como_concluida } />
        </View>
    );
}

const estilo = StyleSheet.create({
    item_lista_container: {
        backgroundColor: 'white',
        padding: 20,
        margin: 20,
        borderRadius: 16
    }
})