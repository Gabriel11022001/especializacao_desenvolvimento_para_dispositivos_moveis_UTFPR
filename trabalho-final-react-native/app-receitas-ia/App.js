import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Home from "./screens/Home";
import Receitas from "./screens/Receitas";
import DetalhesReceita from "./screens/DetalhesReceita";

export default function App() {

  const Stack = createNativeStackNavigator();

  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="Home"
        screenOptions={ {
          headerStyle: {
            backgroundColor: 'orange'
          },
          headerTintColor: 'white'
        } }>
        { /** tela home */ }
        <Stack.Screen
          name="Home"
          component={ Home } />   
        { /** tela com a listagem das receitas */ }
        <Stack.Screen
          name="Receitas"
          component={ Receitas } />    
        { /** tela com os detalhes da receita */ }
        <Stack.Screen
          name="DetalhesReceita"
          component={ DetalhesReceita } />     
      </Stack.Navigator>
    </NavigationContainer>
  );
}
