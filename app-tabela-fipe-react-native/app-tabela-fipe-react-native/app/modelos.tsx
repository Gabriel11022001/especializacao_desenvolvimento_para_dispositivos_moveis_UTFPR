import fetcher from "@/api/fetcher";
import FipeScreen from "@/components/FipeScreen";
import Loader from "@/components/Loder";
import useSWR from 'swr';

export default function Modelos() {

  const { data, error, isLoading } = useSWR('https://parallelum.com.br/fipe/api/v1/carros/marcas//modelos', fetcher)

  if (isLoading) {

    return (
      <Loader />
    );
  } else {

    return (
      <FipeScreen
        // passando os dados como uma propertie
        dados={data}
        onProsseguir={ (codigo: Number) => {
            console.log('Código do modelo: ' + codigo)
        } } />
    );
  }

}
