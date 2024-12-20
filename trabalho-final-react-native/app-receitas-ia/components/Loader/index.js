import { ActivityIndicator, Platform, Text, View } from "react-native";
import estilo from "./estilo";

export default function Loader() {

    return (
        <View style={ estilo.container }>
            <ActivityIndicator
                size="large"
                color="orange" />
            <Text style={ estilo.estiloTextoProgresso }>
                Carregando, aguarde...
            </Text>
        </View>
    );
}