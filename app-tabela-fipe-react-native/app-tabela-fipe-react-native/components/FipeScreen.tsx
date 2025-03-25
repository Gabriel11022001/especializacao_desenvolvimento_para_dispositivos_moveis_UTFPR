import cores from "@/assets/colors/colors";
import { ScrollView, StyleSheet, Text, TextInput, TouchableOpacity, View } from "react-native";
import { FlashList } from "@shopify/flash-list";
import { useEffect, useState } from "react";
import { Marca } from "@/models/Marca";

export default function FipeScreen(props) {

    // const data = ['São Paulo', 'Paraná', 'Bahia']
    const [ textoConsulta, setTextoConsulta ] = useState('')
    const [ dataFiltrada, setDataFiltrada ] = useState<Array<Marca>>([])

    const renderizarDado = (itemRenderizar) => {

        return (<TouchableOpacity style={style.estiloItemLista} onPress={ props.onProsseguir(itemRenderizar.item.codigo) }>
            <Text style={ style.estiloTextoItemLista }>{itemRenderizar.item.nome}</Text>
        </TouchableOpacity>)
    }

    const consultar = (textoDigitado) => {
        const filtro = props.dados.filter(item => item.nome.includes(textoDigitado))

        setDataFiltrada(filtro)
    }

    useEffect(() => {
        consultar(textoConsulta)
    }, [ textoConsulta ])

    return (
        <View style={style.container}>
            { /** campo para o usuário consultar */}
            <TextInput
                placeholder="Digite o que você deseja consultar..."
                style={style.campoConsultar}
                inputMode="search"
                placeholderTextColor={cores.texto}
                value={ textoConsulta }
                onChangeText={ setTextoConsulta } />

            { /** FlashList -> mesma coisa do FlatList mas é mais performatico */}
            <FlashList
                data={ dataFiltrada }
                renderItem={ renderizarDado } />

        </View>
    );
}

const style = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: cores.tint
    },
    campoConsultar: {
        backgroundColor: cores.fundo,
        padding: 25,
        borderRadius: 20,
        margin: 20,
        color: cores.texto,
        fontSize: 18,
        borderColor: cores.borda,
        borderWidth: 1
    },
    estiloItemLista: {
        padding: 20,
        borderColor: cores.borda,
        borderWidth: StyleSheet.hairlineWidth,
        backgroundColor: cores.fundo,
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center'
    },
    estiloTextoItemLista: {
        color: cores.texto,
        fontWeight: 'bold',
        textAlign: 'center'
    }
})