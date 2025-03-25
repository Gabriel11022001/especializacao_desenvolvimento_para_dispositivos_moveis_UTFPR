import { Stack } from "expo-router";

export default function RootLayout() {
  return <Stack>

    { /** adicionar uma Screnn(tela) que aponta para index.tsx */}
    { /** tela inicial do app */}
    <Stack.Screen name="index" options={{
      title: "Tabela fipe"
    }} />

    <Stack.Screen name="modelos" options={{
      title: "Modelos"
    }} />

  </Stack>
}
