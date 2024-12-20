import { Text, TextInput, View } from "react-native";
import estilo from "./estilo";
import { useState } from "react";
import BotaoConsultar from "../../components/BotaoConsultar";
import Loader from "../../components/Loader";
import gerarReceitas from "../../service/gerarReceitas";

export default function Home({ navigation }) {

    const [ ingredientes, setIngredientes ] = useState('');
    const [ apresentarLoader, setApresentarLoader ] = useState(false);

    const pesquisarReceitasIA = async () => {
        
        try {
            
            if (ingredientes.trim() === "") {
                alert("Informe os ingredientes!");
            } else {
                setApresentarLoader(true);
                const resp = await gerarReceitas(ingredientes);
                setApresentarLoader(false);

                // console.log(resp);

                for (let contador = 0; contador < resp.lengt; contador++) {
                    console.log(resp[ contador ]);
                }

                // redirecionar o usuário para a tela para apresentar a listagem de receitas
                navigation.navigate("Receitas", { receitas: resp });
            }

        } catch (e) {
            setApresentarLoader(false);
            alert("Erro: " + e);

            console.log("Erro: " + e);
        }

    }

    return (
        <View style={ estilo.container }>
            <Text style={ estilo.txtTitulo }>
                Gerador de receitas utilizando IA
            </Text>
            <Text style={ estilo.txtInformeIngredientes }>
                Informe os ingredientes da receita no campo abaixo para a consulta.
            </Text>
            <TextInput 
                inputMode="text"
                value={ ingredientes }
                onChangeText={ (ingredientesDigitadosCampo) => setIngredientes(ingredientesDigitadosCampo) }
                style={ estilo.estiloCampoIngredientes }
                placeholder="Ex: Maçâ, Banana, Graviola, Nutela, etc..." />
            <BotaoConsultar onPesquisar={ () => {
                pesquisarReceitasIA();
            } } />
            { apresentarLoader ? <Loader /> : null }
        </View>
    );
}