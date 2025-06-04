# Última Fronteira
Última Fronteira é um jogo de sobrevivência 2D com elementos de RPG, desenvolvido em Java com Swing como parte da disciplina de Linguagem de Programação Orientada a Objetos (LPOO). O jogo se passa em um mundo pós-apocalíptico onde os jogadores devem explorar ambientes hostis, coletar recursos, enfrentar criaturas e gerenciar suas necessidades vitais para garantir sua sobrevivência.

# Visão Geral
Num mundo devastado, onde a natureza resiste e a tecnologia ruiu, poucos permanecem. Você é parte dos últimos sobreviventes em busca de um novo começo na Última Fronteira. Cada passo será decisivo para seu futuro. Explore, colete e lute para ver um novo amanhecer.

# Principais Funcionalidades (Features)
Mundo 2D Explorável: Explore 5 biomas distintos (Floresta, Caverna, Lago/Rio, Montanha e Ruínas), cada um com recursos, perigos e eventos.
Sistema de Sobrevivência Complexo: Gerencie 5 status vitais: Vida, Fome, Sede, Energia e Sanidade. A negligência de qualquer um deles pode levar a penalidades e até à morte.

Múltiplas Classes de Personagem: Escolha entre 4 classes de personagens, cada uma com uma vantagem única, demonstrando o uso de Herança e Polimorfismo.

Eventos Dinâmicos: O mundo é vivo e imprevisível. Enfrente eventos climáticos como chuvas e nevascas, encontre criaturas e sofra com doenças como desidratação e infecções.

Combate em Tempo Real: Enfrente criaturas hostis e cace presas pacíficas usando um sistema de combate em tempo real baseado em colisão de ataque.

Coleta e Interação com o Ambiente: Use ferramentas para derrubar árvores e minerar rochas, coletando materiais essenciais para a sua jornada.

Sistema de Inventário e Itens: Colete e gerencie um inventário com diversos itens, incluindo alimentos, armas, ferramentas e remédios, cada um com seu próprio uso e descrição.

Ciclo de Dia/Noite e Iluminação: O jogo possui um sistema de turnos de dia e noite que afeta o ambiente, com um efeito de iluminação dinâmico que cria um foco de luz ao redor do jogador durante a escuridão.

# Como Jogar
Controles
WASD: Movimentar o personagem

Enter: Confirmar / Atacar / Interagir 

C: Abrir/Fechar Inventário e Status

P: Pausar o Jogo 

ESC: Abrir Menu de Opções / Voltar

M: Abrir/Fechar Descrição do Mapa

# Objetivo
O objetivo principal é sobreviver o maior tempo possível. Para isso, o jogador precisa explorar os diferentes mapas em busca de recursos, gerenciar seus status de fome, sede e sanidade, e se defender das criaturas que habitam este mundo desolado.

Estrutura do Projeto e Destaques de LPOO:

O projeto foi estruturado com uma clara separação de responsabilidades entre a lógica do jogo (backend) e a interface gráfica (frontend), demonstrando uma forte aplicação dos princípios de Programação Orientada a Objetos.

# Arquitetura
Backend (Pacotes personagens, itens, eventos, ambientes, turnos): Contém as classes que definem as regras, os dados e a lógica do jogo. Representam o "modelo" do mundo de forma abstrata.

Frontend (Pacotes gui): Responsável pela renderização, animação, captura de input e apresentação de todos os elementos visuais e sonoros ao jogador. As classes neste pacote (como PainelJogo, Entidade e InterfaceUsuario) consomem a lógica do backend para criar a experiência interativa.

Aplicação de Conceitos de LPOO

Herança: A herança é amplamente utilizada para criar especializações e reutilizar código.
gui.entidades.Entidade é a base para gui.entidades.Jogador, que por sua vez é a superclasse de Doctor, Mechanic, etc.
itens.Item é a base para Alimento, Arma, Ferramenta.
eventos.Evento é a base para EventoClimatico, EventoCriatura.
ambientes.Ambiente é a base para AmbienteFloresta, AmbienteCaverna.
Abstração: Classes abstratas como Item, Evento e Ambiente definem um contrato comum para um conjunto de subclasses, escondendo os detalhes de implementação.

Polimorfismo: O polimorfismo é evidente em métodos como:
usar() nas subclasses de Item, que executa uma ação diferente para um Alimento ou Remedio.
executar() nas subclasses de Evento, que dispara um acontecimento único para EventoClimatico ou EventoCriatura.
desenhar() na hierarquia de Entidade, que renderiza cada entidade de forma específica.

Encapsulamento: Os atributos das classes são mantidos como private, e o acesso a eles é controlado por meio de métodos getters e setters, protegendo o estado interno dos objetos.

# Tecnologias Utilizadas
Linguagem: Java

Interface Gráfica: Java Swing

Áudio: javax.sound.sampled para reprodução de música e efeitos sonoros.

Como Executar/Pré-requisitos:

Ter o Java Development Kit (JDK) instalado em sua máquina.

Execução:

Compile todos os arquivos .java do projeto.

Execute a classe sistema.Main que contém o método main.

# Autores
Diego Nery Romeiro e Letícia Rodrigues Quirino