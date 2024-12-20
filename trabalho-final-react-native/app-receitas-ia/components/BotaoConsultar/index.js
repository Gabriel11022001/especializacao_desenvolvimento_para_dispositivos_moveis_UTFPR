import { Text, TouchableOpacity } from "react-native";
import estilo from "./estilo";

export default function BotaoConsultar({ onPesquisar }) {

    return (
        <TouchableOpacity 
            style={estilo.estiloBotao }
            onPress={ onPesquisar } >
            <Text style={ estilo.txtBtnConsultar }>Consultar receitas</Text>
        </TouchableOpacity>
    );
}
