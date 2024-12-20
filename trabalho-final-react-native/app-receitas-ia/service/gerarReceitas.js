const Groq = require('groq-sdk');

// função async responsável por gerar as receitas a partir dos ingredientes que o usuário fornecer
async function gerarReceitas(ingredientes) {
    const groq = new Groq({ apiKey: "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" });

    const resposta = await groq.chat.completions.create({
        "messages": [
            {
                "role": "user",
                "content": "vou te fornecer uma lista de ingredientes, quero que você me retorne um array no formato json contendo todas as receitas que possuem os ingredientes que eu fornecer\nquero receitas de todo o mundo, de todas as culturas\n\nquero que você me retorne o nome, categoria, a lista de ingredientes, o modo de preparo e uma foto da receita\n\nme retorne um array do javascript com as receitas sendo representadas por objetos do javascript\n\neu quero somente o array"
            },
            {
                "role": "assistant",
                "content": "Estou ansioso para ajudar!\n\nPara obter as receitas que contenham os ingredientes fornecidos, eu preciso saber quais são esses ingredientes. Por favor, forneça a lista de ingredientes.\n\n(Este é um modelo de como eu farei: eu buscarei receitas em variadas fontes e categorias e, se uma receita tiver todos os ingredientes fornecidos, adicionarei ela ao array.)\n\nPor exemplo, se você fornecer a lista de ingredientes `[\"tomate\", \"cebola\", \"carne\"]`, eu retornarei um array como este:\n```json\n[\n  {\n    \"nome\": \"Bobó de Nhaque\",\n    \"categoria\": \"Brasileiro\",\n    \"ingredientes\": [\"tomate\", \"cebola\", \"carne\"],\n    \"modoDePreparo\": \"Assiste o vídeo para saber como fazer!\",\n    \"foto\": \"https://-exemplo.com/bobo-nhaque.jpg\"\n  },\n  {\n    \"nome\": \"Stufato alla Toscana\",\n    \"categoria\": \"Italiano\",\n    \"ingredientes\": [\"tomate\", \"cebola\", \"carne\"],\n    \"modoDePreparo\": \"Queime a cebola em água e adicione a carne...\",\n    \"foto\": \"https://-exemplo.com/stufato-toscana.jpg\"\n  }\n]\n```\nE assim por diante."
            },
            {
                "role": "user",
                "content": "frango, queijo, pão francês"
            },
            {
                "role": "assistant",
                "content": "Mais uma lista de ingredientes!\n\nAqui está o array de receitas que contém os ingredientes \"frango\", \"queijo\" e \"pão francês\":\n```json\n[\n  {\n    \"nome\": \"Croque-Madame\",\n    \"categoria\": \"Francês\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Frescura queijo e abate, assa até dourar.\",\n    \"foto\": \"https://-exemplo.com/croque-madame.jpg\"\n  },\n  {\n    \"nome\": \"Cheesesteak\",\n    \"categoria\": \"Americano\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Assa o frango, coloque queijo e pão, e abate.\",\n    \"foto\": \"https://-exemplo.com/cheesesteak.jpg\"\n  },\n  {\n    \"nome\": \"Frango com Queijo e Pão\",\n    \"categoria\": \"Geral\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Assa o frango, coloque queijo e pão, e abate.\",\n    \"foto\": \"https://-exemplo.com/frango-queijo-pao.jpg\"\n  }\n]\n```\nAssista aqui um pouco de internacionalidade: um croque-madame francês, um cheesesteak americano e uma receita mais geral que combina os ingredientes!\n\nVocê gostou?"
            },
            {
                "role": "user",
                "content": "quero somente o array, não quero outras mensagens"
            },
            {
                "role": "assistant",
                "content": "[\n  {\n    \"nome\": \"Croque-Madame\",\n    \"categoria\": \"Francês\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Frescura queijo e abate, assa até dourar.\",\n    \"foto\": \"https://-exemplo.com/croque-madame.jpg\"\n  },\n  {\n    \"nome\": \"Cheesesteak\",\n    \"categoria\": \"Americano\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Assa o frango, coloque queijo e pão, e abate.\",\n    \"foto\": \"https://-exemplo.com/cheesesteak.jpg\"\n  },\n  {\n    \"nome\": \"Frango com Queijo e Pão\",\n    \"categoria\": \"Geral\",\n    \"ingredientes\": [\"frango\", \"queijo\", \"pão francês\"],\n    \"modoDePreparo\": \"Assa o frango, coloque queijo e pão, e abate.\",\n    \"foto\": \"https://-exemplo.com/frango-queijo-pao.jpg\"\n  }\n]"
            },
            {
                "role": "user",
                "content": ingredientes.trim()
            }
        ],
        "model": "llama-3.1-70b-versatile"
    });

    console.log(JSON.parse(resposta.choices[0].message.content));

    return JSON.parse(resposta.choices[0].message.content);
}

export default gerarReceitas;