const Groq = require('groq-sdk');

// função para gerar as respostas
export async function geradorDeDesculpas(desculpa) {

    const groq = new Groq({
        apiKey: "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"
    });

    const chatCompletion = await groq.chat.completions.create({
        "messages": [
            {
                "role": "user",
                "content": "eu vou criar um app onde eu informo uma desculpa esfarrapada para algo, e eu quero que você de devolva essa desculpa em formato de texto, a desculpa deve ser engraçada e não ofender ninguém."
            },
            {
                "role": "assistant",
                "content": "Que ideia interessante! Eu posso definitivamente ajudar você com isso. Aqui vai uma desculpa esfarrapada engraçada que você pode usar como exemplo:\n\n\"Desculpe por não ter aparecido no compromisso, mas eu estava tendo um problema sério com minha planta de interior. Ela estava com um problema de estresse por excesso de água e eu precisava fazer uma sessão de terapia com ela para que ela se sentisse melhor. Eu sei que parece loucura, mas eu sou o único que ela tem no mundo, então eu precisava estar lá para ela. E agora que estou aqui, posso explicar melhor.\"\n\nSe isso não for o que você está procurando, posso tentar criar outra desculpa. Você tem algum tema ou situação específica em mente para a desculpa? Por exemplo, não ter feito uma tarefa no trabalho, ter perdido um compromisso de namorados, etc.?"
            },
            {
                "role": "user",
                "content": "me devolva somente a desculpa"
            },
            {
                "role": "assistant",
                "content": "Desculpe por não ter aparecido no compromisso, mas eu estava tendo um problema sério com minha planta de interior. Ela estava com um problema de estresse por excesso de água e eu precisava fazer uma sessão de terapia com ela para que ela se sentisse melhor."
            },
            {
                "role": "user",
                "content": desculpa.trim()
            }
        ],
        "model": "llama-3.1-70b-versatile"
    });

    console.log(JSON.stringify(chatCompletion.choices[0].message.content));

    return chatCompletion.choices[0].message.content;
}