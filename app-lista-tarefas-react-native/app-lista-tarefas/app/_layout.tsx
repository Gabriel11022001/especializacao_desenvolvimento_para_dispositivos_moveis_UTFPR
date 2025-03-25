import { Stack } from "expo-router";

export default function RootLayout() {

  // Stack -> empilhar as telas uma encima da outra
  return (
    <Stack>

      <Stack.Screen
        name="index"
        options={{
          title: "Home",
          headerStyle: {
            backgroundColor: 'blue'
          },
          headerTintColor: 'white'
        }} />

    </Stack>
  );
}
