package gui.system;


//java.awt é um conjunto de classes do Java que permite criar interfaces gráficas
//java.swing

import gui.ambientacao.GerenciadorAmbientacao;
import gui.blocos.GerenciadorBlocos;
import gui.entidades.*;
import gui.eventos.EventoClima;
import gui.eventos.EventoCriatura;
import gui.eventos.EventoDescoberta;
import gui.eventos.ManipuladorDeEventos;
import gui.tile_interativo.BlocoInterativo;
import sistema.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class PainelJogo extends JPanel implements Runnable { //GamePanel herda de JPanel, que é a área onde o jogo vai ser desenhado
    //implements Runnable indica que essa classe será executada em uma Thread separada, permitindo que o jogo rode continuamente sem travar a interface
    private int tremorDuracao = 0; // conta frames de tremor
    private int offsetX = 0;
    private int offsetY = 0;

    public void iniciarTremor(int duracaoFrames) {
        tremorDuracao = duracaoFrames;
    }

    // Atributos privados

    public Jogador jogador;

    private final int tamanhoOriginalBloco = 16; //O tamanho de um bloco do jogo é 16 pixels
    private final int escala = 3; //O tamanho do bloco será escalada em 3 vezes, ficando com 48 pixels

    private final int tamanhoBloco = tamanhoOriginalBloco * escala;
    private final int tamanhoColuna = 20; //O jogo terá 16 colunas
    private final int tamanhoLinha = 12; //O jogo terá 12 linhas
    private final int telaLargura = tamanhoBloco * tamanhoColuna;
    private final int telaAltura = tamanhoBloco * tamanhoLinha;

    private final int linhaMundoMax = 50;
    private final int colMundoMax = 50;
    private final int mundoLargura = tamanhoBloco * linhaMundoMax;
    private final int mundoAltura = tamanhoBloco * colMundoMax;

    private String personagemSelecionado;
    private boolean jogoIniciado = false;

    // Tela cheia
    private int telaLargura2 = telaLargura;
    private int telaAltura2 = telaAltura;
    private BufferedImage tempoTela;
    private Graphics2D g2;
    private boolean telaCheiaOn=false;
    private int FPS = 90;

    // Sistema

    private GerenciadorBlocos blocosG = new GerenciadorBlocos(this);//

    private EventosTeclado eventosTeclado = new EventosTeclado(this); //Cria um objeto que lida com os eventos do teclado.

    private ChecadorColisoes cColisoes = new ChecadorColisoes(this);

    private EventoCriatura eventoCriatura=new EventoCriatura(this);

    private EventoDescoberta eventoDescoberta=new EventoDescoberta(this);

    private InterfaceUsuario iu = new InterfaceUsuario(this);

    private ManipuladorDeEventos manipuladorDeEventos = new ManipuladorDeEventos(this);

    private GerenciadorAmbientacao gerenciadorAmbientacao= new GerenciadorAmbientacao(this);

    Thread threadJogo;

    // Mapa do jogo

    private final int mapaMax = 10;
    private int mapaAtual = 0;

    // Entidade e objeto

    //public Jogador jogador = new Jogador(this, eventosTeclado);

    private Som som = new Som();
    private Entidade obj[][] = new Entidade[mapaMax][100];
    private Entidade npc[][] = new Entidade[mapaMax][100];
    private Entidade criatura[][] = new Entidade[mapaMax][20];
    private Entidade presa[][] = new Entidade[mapaMax][20];
    private BlocoInterativo bloco[][]=new BlocoInterativo[mapaMax][30];
    private ArrayList<Entidade> entidadeLista = new ArrayList<>();
    private Entidade alimento[][] = new Entidade[mapaMax][10];
    public Entidade fogueira[][]=new Entidade[mapaMax][10];
    public Entidade craft[][]=new Entidade[mapaMax][10];
    public Entidade aquatico[][]= new Entidade[mapaMax][10];


    // Estado do jogo

    private int estadoJogo;

    private final int estadoTitulo = 0;

    private final int estadoPlay = 1;
    private final int estadoPausa = 2;
    private final int estadoDialogo = 4;
    private int estadoDescricao = 3;
    private int estadoPersonagem = 5;
    private int estadoJogoFinalizado = 6;
    private int estadoJogoDescricao = 7;
    private int estadoOpcoes = 8;
    private int estadoAssarAlimento=9;
    private int estadoTransicao=10;
    private int estadoSistemaDeTroca=11;
    private int estadoJogoVencido=12;


    // Métodos de acesso getters


    public int getEstadoJogoVencido() {
        return estadoJogoVencido;
    }

    public void setEstadoJogoVencido(int estadoJogoVencido) {
        this.estadoJogoVencido = estadoJogoVencido;
    }

    public Entidade[][] getCraft() {
        return craft;
    }

    public int getEstadoSistemaDeTroca() {
        return estadoSistemaDeTroca;
    }


    public int getEstadoPersonagem() {
        return estadoPersonagem;
    }

    public final int getTamanhoBloco() {
        return tamanhoBloco;
    }

    public final int getTamanhoColuna() {
        return tamanhoColuna;
    }

    public final int getTamanhoLinha() {
        return tamanhoLinha;
    }

    public final int getTelaLargura() {
        return telaLargura;
    }

    public final int getTelaAltura() {
        return telaAltura;
    }

    public final int getLinhaMundoMax() {
        return linhaMundoMax;
    }

    public boolean isTelaCheiaOn() {
        return telaCheiaOn;
    }

    public void setTelaCheiaOn(boolean telaCheiaOn) {
        this.telaCheiaOn = telaCheiaOn;
    }

    public final int getColMundoMax() {
        return colMundoMax;
    }

    public final int getMundoLargura() {
        return mundoLargura;
    }

    public final int getMundoAltura() {
        return mundoAltura;
    }

    public ChecadorColisoes getcColisoes() {
        return cColisoes;
    }

    public InterfaceUsuario getIu() {
        return iu;
    }

    public Som getSom() {
        return som;
    }

    public String getPersonagemSelecionado() {
        return personagemSelecionado;
    }

    public int getEstadoTitulo() {
        return estadoTitulo;
    }

    public GerenciadorBlocos getBlocosG() {
        return blocosG;
    }

    public Entidade[][] getObj() {
        return obj;
    }

    public Entidade[][] getPresa() {
        return presa;
    }

    public BlocoInterativo[][]getBloco(){
        return bloco;
    }

    public boolean isJogoIniciado() {
        return jogoIniciado;
    }

    public int getEstadoJogo() {
        return estadoJogo;
    }

    public int getEstadoJogoFinalizado() {
        return estadoJogoFinalizado;
    }

    public int getEstadoOpcoes() {
        return estadoOpcoes;
    }

    public final int getEstadoPlay() {
        return estadoPlay;
    }

    public final int getEstadoPausa() {
        return estadoPausa;
    }

    public int getEstadoJogoDescricao() {
        return estadoJogoDescricao;
    }

    public int getEstadoAssarAlimento() {
        return estadoAssarAlimento;
    }


    public Entidade[][] getNpc() {
        return npc;
    }

    public ManipuladorDeEventos getManipuladorDeEventos() {
        return manipuladorDeEventos;
    }

    public Entidade[][] getCriatura() {
        return criatura;
    }

    public int getEstadoDialogo() {
        return estadoDialogo;
    }

    public EventosTeclado getEventosTeclado() {
        return eventosTeclado;
    }

    public ArrayList<Entidade> getEntidadeList() {
        return entidadeLista;
    }

    public Entidade[][] getAlimento() {
        return alimento;
    }

    public final int getMapaMax() {
        return mapaMax;
    }

    public int getMapaAtual() {
        return mapaAtual;
    }

    public int getEstadoTransicao() {
        return estadoTransicao;
    }

    // Métodos setters

    public void setPersonagemSelecionado(String personagemSelecionado) {
        this.personagemSelecionado = personagemSelecionado;
    }

    public void setObj(Entidade[][] obj) {
        this.obj = obj;
    }

    public void setEstadoJogo(int estadoJogo) {
        this.estadoJogo = estadoJogo;
    }

    public void setEstadoPlay(int estadoPlay) {
        this.estadoJogo = estadoPlay;
    }

    public void setEstadoPausa(int estadoPausa) {
        this.estadoJogo = estadoPausa;
    }

    public void setMapaAtual(int mapaAtual) {
        this.mapaAtual = mapaAtual;
    }

    public void setBlocosG(GerenciadorBlocos blocosG) {
        this.blocosG = blocosG;
    }

    public void setManipuladorDeEventos() {
        this.manipuladorDeEventos = manipuladorDeEventos;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setEntidadeLista(ArrayList<Entidade> entidadeLista) {
        this.entidadeLista = entidadeLista;
    }

    public void setEstadoOpcoes(int estadoOpcoes) {
        this.estadoOpcoes = estadoOpcoes;
    }
    public void setEstadoAssarAlimento(int estadoAssarAlimento) {
        this.estadoAssarAlimento = estadoAssarAlimento;
    }


    public PainelJogo() {

        this.setPreferredSize(new Dimension(telaLargura, telaAltura)); //Define o tamanho da tela
        this.setBackground(Color.black); //Define o fundo como preto
        this.setDoubleBuffered(true); //Ativa o "Double Buffering"(Isso ajuda a evitar que a tela fique piscando ao desenhar elementos)
        this.addKeyListener(eventosTeclado); //Adiciona o KeyListener para capturar teclas pressionadas
        this.setFocusable(true); //Permite que o painel capture eventos de teclado
        this.requestFocus(); //Garante que o painel receba o foco do teclado:

    }

    public void iniciarThreadJogo() {

        threadJogo = new Thread(this);
        threadJogo.start();

    }

    public void retry(){
        jogador.posicoesPadrao();
        jogador.restaurarVida();
        eventoDescoberta.definirObjeto();
        eventoDescoberta.definirNPC();
        eventoCriatura.definirCriatura();
        eventoCriatura.definirPresa();

    }


    // Sair do jogo
    public void restart(){
        jogador = null;

        setPersonagemSelecionado(null); //

        eventoDescoberta.definirObjeto();
        eventoDescoberta.definirNPC();
        eventoCriatura.definirCriatura();
        eventoCriatura.definirPresa();
        eventoDescoberta.definirBlocoInterativo();


        if (iu != null) {
            iu.setPersonagemSelecionado(null);
            iu.setTelaMenu(0); //
            iu.setComandoNum(0);
        }
        mapaAtual=0;

        System.out.println("Jogo reiniciado - personagem resetado para nova seleção");
    }

    @Override
    public void run() { // Loop principal do jogo (mantém o jogo rodando enquanto a threadJogo estiver ativa)

        double intervaloDesenho = 1000000000 / FPS; // Tempo ideal entre os quadros
        double delta = 0;
        long ultimoTempo = System.nanoTime();
        long tempoAtual;
        long cronometro = 0;
        int contadorQuadros = 0;

        while (threadJogo!=null) {
            tempoAtual = System.nanoTime();

            delta += (tempoAtual - ultimoTempo) / intervaloDesenho;
            cronometro += (tempoAtual - ultimoTempo);

            ultimoTempo = tempoAtual;

            if (delta >= 1) { // Só atualiza o jogo quando delta ≥ 1
                update();    // Atualiza a lógica do jogo
                //repaint();      // Requisição para redesenhar a tela
                desenharTelaTemp();
                desenharTela();
                delta--;        // Evita múltiplas atualizações por quadro
                contadorQuadros++;
            }


            try {
                Thread.sleep(1); // Pequena pausa para reduzir o uso da CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (cronometro >= 1000000000) { // A cada 1 segundo...
            System.out.println("FPS: " + contadorQuadros);
            contadorQuadros = 0;
            cronometro = 0;
        }

    }

    public void criarJogadorConformeEscolha() {
        String personagemKey = getPersonagemSelecionado();

        switch (personagemKey) {
            case "rastreador":
                jogador = new Detective(this, eventosTeclado);

                break;
            case "medico":
                jogador = new Doctor(this, eventosTeclado);
                break;
            case "mecanica":
                jogador = new Mechanic(this, eventosTeclado);
                break;
            case "sobrevivente":
                jogador = new Survivor(this, eventosTeclado);
                break;
            default:
                jogador = new Jogador(this, eventosTeclado);
                break;
        }


    }

    public void update() {
        if (estadoJogo == estadoPlay) {
            // Só criar o jogador se ele for null E se tivermos uma seleção válida
            if (jogador == null) {
                String personagemSelecionado = getPersonagemSelecionado();
                if (personagemSelecionado != null && !personagemSelecionado.isEmpty()) {
                    criarJogadorConformeEscolha();
                    jogador.setVelocidade(4);
                    jogador.setValoresPadrao();
                    jogador.posicoesPadrao();
                    jogador.restaurarVida();
                    jogador.definirItens();

                    // Atualiza a iluminação para seguir o jogador
                    int tamanhoCirculo = 200; // ou outro valor adequado
                    int centroX = jogador.getTelaX() + (getTamanhoBloco() / 2);
                    int centroY = jogador.getTelaY() + (getTamanhoBloco() / 2);
                    gerenciadorAmbientacao.setup(tamanhoCirculo, centroX, centroY);
                } else {
                    estadoJogo = estadoTitulo;
                    return;
                }
            }

            if (jogador != null) {
                jogador.update();
            }


            for (int i = 0; i < aquatico[1].length; i++) {
                if (aquatico[mapaAtual][i] != null) {
                    aquatico[mapaAtual][i].update();
                }
            }

            // NPC

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[mapaAtual][i] != null) {
                    npc[mapaAtual][i].update();
                }
            }

            for (int i = 0; i < craft[1].length; i++) {
                if (craft[mapaAtual][i] != null) {
                    craft[mapaAtual][i].update();
                }
            }

            // Criatura
            for (int i = 0; i < criatura[1].length; i++) {
                if (criatura[mapaAtual][i] != null) {
                    if(criatura[mapaAtual][i].isVivo()==true && criatura[mapaAtual][i].isMorto()==false){
                        criatura[mapaAtual][i].update();
                    }
                    if(criatura[mapaAtual][i].isVivo()==false){
                        criatura[mapaAtual][i].checarDrop();
                        criatura[mapaAtual][i]=null;
                    }
                }
            }

            // Presa

            for (int i = 0; i < presa[1].length; i++) {
                if (presa[mapaAtual][i] != null) {
                    if(presa[mapaAtual][i].isVivo()==true && presa[mapaAtual][i].isMorto()==false){
                        presa[mapaAtual][i].update();
                    }
                    if(presa[mapaAtual][i].isVivo()==false){
                        presa[mapaAtual][i].checarDrop();
                        presa[mapaAtual][i]=null;
                    }
                }
            }

            // Tile interativo
            for (int i = 0; i < bloco[1].length; i++) {
                if (bloco[mapaAtual][i] != null) {
                    bloco[mapaAtual][i].update();
                }
            }

            gerenciadorAmbientacao.update();
        }

        if (estadoJogo == estadoPausa) {

        }
    }

    public void setupJogo() {
        eventoDescoberta.definirObjeto();
        eventoDescoberta.definirNPC();
        eventoCriatura.definirCriatura();
        eventoCriatura.definirPresa();
        eventoDescoberta.definirBlocoInterativo();
        //eventoCriatura.definirAquatico();


        // Configura a iluminação inicial (centro da tela)
        int tamanhoCirculo = 200; // ou outro valor adequado
        int centroX = getTelaLargura() / 2;
        int centroY = getTelaAltura() / 2;
        gerenciadorAmbientacao.setup(tamanhoCirculo, centroX, centroY);

        playMusica(0);
        estadoJogo = estadoTitulo;

        tempoTela = new BufferedImage(telaLargura, telaAltura, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempoTela.getGraphics();

        definirTelaCheia();
    }

    public void definirTelaCheia(){
        
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd=ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        
        telaLargura2=Main.window.getWidth();
        telaAltura2=Main.window.getHeight();
    }


    // Tela de clima

    // Atributos privados

    private ArrayList<Gota> gotas = new ArrayList<>();
    private boolean mostrarChuva = false;
    private ArrayList<FlocoDeNeve> flocos = new ArrayList<>();
    private boolean mostrarNevasca = false;
    private boolean mostrarEfeitoConfusao = false;
    private boolean mostraEfeitoDesmoronamento=false;
    private float angulo = 0;
    private Random random = new Random();

    // Métodos de acesso


    public void setMostraEfeitoDesmoronamento(boolean mostraEfeitoDesmoronamento) {
        this.mostraEfeitoDesmoronamento = mostraEfeitoDesmoronamento;
    }

    public boolean isMostraEfeitoDesmoronamento() {
        return mostraEfeitoDesmoronamento;
    }

    public boolean isMostrarChuva(){
        return mostrarChuva;
    }

    public boolean isMostrarEfeitoConfusao() {
        return mostrarEfeitoConfusao;
    }

    public void setMostrarNevasca(boolean mostrarNevasca) {
        this.mostrarNevasca = mostrarNevasca;
    }

    public void setMostrarEfeitoConfusao(boolean mostrarEfeitoConfusao) {
        this.mostrarEfeitoConfusao = mostrarEfeitoConfusao;
    }

    public void setMostrarChuva(boolean mostrarChuva) {
        this.mostrarChuva = mostrarChuva;
    }

    // Classe interna Gota

    public class Gota {
        int x, y, velocidade;

        Gota(int x, int y, int velocidade) {
            this.x = x;
            this.y = y;
            this.velocidade = velocidade;
        }
    }

    // Classe interna Flocos de neve

    public class FlocoDeNeve {
        int x, y, tamanho, velocidade;

        public FlocoDeNeve(int x, int y, int tamanho, int velocidade) {
            this.x = x;
            this.y = y;
            this.tamanho = tamanho;
            this.velocidade = velocidade;
        }
    }

    // Método e iniciar chuva

    public void iniciarChuva(int quantidade) {
        gotas.clear();
        for (int i = 0; i < quantidade; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int velocidade = 2 + random.nextInt(3);
            gotas.add(new Gota(x, y, velocidade));
        }
        mostrarChuva = true;
    }

    // Método de iniciar nevasca

    public void iniciarNevasca(int quantidade) {
        flocos.clear();
        for (int i = 0; i < quantidade; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int tamanho = 5 + random.nextInt(5);
            int velocidade = 1 + random.nextInt(3);
            flocos.add(new FlocoDeNeve(x, y, tamanho, velocidade));
        }
        mostrarNevasca = true;
    }

    // Desenhar telas de clima

    public void desenharChuva(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(150, 150, 255, 100));
        for (Gota gota : gotas) {
            g2.drawLine(gota.x, gota.y, gota.x, gota.y + 10);
            gota.y += gota.velocidade;
            if (gota.y > getHeight()) {
                gota.y = 0;
                gota.x = random.nextInt(getWidth());
            }
        }
    }

    public void desenharNevasca(Graphics2D g2) {
        g2.setColor(new Color(169, 169, 169, 200));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(255, 255, 255, 150));
        for (FlocoDeNeve floco : flocos) {
            g2.fillOval(floco.x, floco.y, floco.tamanho, floco.tamanho);
            floco.y += floco.velocidade;
            if (floco.y > getHeight()) {
                floco.y = 0;
                floco.x = random.nextInt(getWidth());
            }
        }
    }

    public void desenharEfeitoConfusao(Graphics2D g2) {
        int largura = getWidth();
        int altura = getHeight();

        // Simula tremor da tela
        int tremorX = (int)(Math.random() * 10 - 5); // de -5 a 5
        int tremorY = (int)(Math.random() * 10 - 5); // de -5 a 5

        AffineTransform saveAT = g2.getTransform();
        g2.translate(tremorX, tremorY);

        // Efeito vermelho translúcido sobre a tela
        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(0, 0, largura, altura);

        g2.setTransform(saveAT); // restaura a posição normal para não afetar o resto do jogo
    }

    private int contadorDesmoronamento = 0;

    public void desenharEfeitoDesmoronamento(Graphics2D g2) {
        int largura = getWidth();
        int altura = getHeight();

        contadorDesmoronamento++;

        // Camada de tremor: desenha 5 vezes com deslocamento e sobreposição translúcida
        for (int i = 0; i < 5; i++) {
            int tremorX = (int) (Math.random() * 16 - 8);  // de -8 a +8
            int tremorY = (int) (Math.random() * 16 - 8);  // de -8 a +8

            // Simula deslocamento visual com sobreposição translúcida
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f)); // bem suave
            g2.setColor(Color.BLACK);
            g2.fillRect(tremorX, tremorY, largura, altura);
        }

        // Camada de escurecimento geral (mais forte, mas ainda translúcida)
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, largura, altura);

        // Flash vermelho intermitente e translúcido
        if (contadorDesmoronamento % 12 < 6) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2.setColor(Color.RED);
            g2.fillRect(0, 0, largura, altura);
        }

        // Reset da transparência para não afetar outros desenhos
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        iniciarTremor(30);
    }

    public void desenharTelaTemp(){

        long desenhoComeco = 0;

        if (tremorDuracao > 0) {
            offsetX = (int)(Math.random() * 10 - 5); // desloca de -5 a 5 px
            offsetY = (int)(Math.random() * 10 - 5);
            tremorDuracao--;
        } else {
            offsetX = 0;
            offsetY = 0;
        }
        AffineTransform original = g2.getTransform();
        g2.translate(offsetX, offsetY);
        if(eventosTeclado.isChecarDesenhoTempo()==true){
            desenhoComeco=System.nanoTime();
        }

        // TELA DE TÍTULO
        if (estadoJogo == estadoTitulo) {
            iu.desenhar(g2);
        } else {

            // Desenhar blocos do mapa
            blocosG.draw(g2);

            for (int i = 0; i < bloco[1].length; i++) {
                if (bloco[mapaAtual][i] != null) {
                    bloco[mapaAtual][i].desenhar(g2);
                }
            }

            for (int i = 0; i < craft[1].length; i++) {
                if (craft[mapaAtual][i] != null) {
                    entidadeLista.add(craft[mapaAtual][i]);
                }
            }

            // Adicionar entidades à lista de desenho


            entidadeLista.add(jogador);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[mapaAtual][i] != null) {
                    entidadeLista.add(npc[mapaAtual][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[mapaAtual][i] != null) {
                    entidadeLista.add(obj[mapaAtual][i]);
                }
            }

            for (int i = 0; i < criatura[1].length; i++) {
                if (criatura[mapaAtual][i] != null) {
                    entidadeLista.add(criatura[mapaAtual][i]);
                }
            }

            for (int i = 0; i < presa[1].length; i++) {
                if (presa[mapaAtual][i] != null) {
                    entidadeLista.add(presa[mapaAtual][i]);
                }
            }

            for (int i = 0; i < aquatico[1].length; i++) {
                if (aquatico[mapaAtual][i] != null) {
                    entidadeLista.add(aquatico[mapaAtual][i]);
                }
            }

            g2.setTransform(original);




            // Ordenar entidades pelo eixo Y
            Collections.sort(entidadeLista, new Comparator<Entidade>() {
                @Override
                public int compare(Entidade e1, Entidade e2) {
                    int resultado=Integer.compare(e1.getMundoY(), e2.getMundoY());
                    return resultado;
                }
            });

            // Desenhar todas as entidades na ordem correta
            for(int i=0; i<entidadeLista.size(); i++){
                entidadeLista.get(i).desenhar(g2);
            }
            entidadeLista.clear();


            // Efeitos visuais
            if (mostrarEfeitoConfusao) {
                desenharEfeitoConfusao(g2);
            }

            if (mostrarChuva) {
                desenharChuva(g2);
            }
            if (mostrarNevasca) {
                desenharNevasca(g2);
            }

            if(mostraEfeitoDesmoronamento){
                desenharEfeitoDesmoronamento(g2);
            }
            // IU (Interface do Usuário)
            gerenciadorAmbientacao.desenhar(g2);
            iu.desenhar(g2);

        }

        if (eventosTeclado.isMostrarTextoDebug()) {
            long desenhoFinal=System.nanoTime();
            long passou=desenhoFinal-desenhoComeco;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x=10;
            int y=400;
            int linhaLargura=20;

            g2.drawString("Mundo X: " + jogador.getMundoX(), x, y); y+=linhaLargura;
            g2.drawString("Mundo Y: " + jogador.getMundoY(), x, y); y+=linhaLargura;
            g2.drawString("Coluna: " + (jogador.getMundoX() + jogador.getAreaSolida().x)/tamanhoBloco, x, y); y+=linhaLargura;
            g2.drawString("Linha: " + (jogador.getMundoY() + jogador.getAreaSolida().y)/tamanhoBloco, x, y); y+=linhaLargura;

            g2.drawString("Draw time: "+ passou, 10,480);
            System.out.println("Draw time: "+ passou);
        }

        long desenhoFinal=System.nanoTime();
        long passado= desenhoFinal-desenhoComeco;


    }

    public void desenharTela(){
        Graphics g=getGraphics();
        g.drawImage(tempoTela,0,0,telaLargura2, telaAltura2, null);
        g.dispose();
    }

    public void playMusica(int i){
        som.pegarArquivo(i);
        som.play();
        som.loop();

    }
    public void pararMusica(int i){

        som.pegarArquivo(i);
        som.stop();
    }

    public void playSE(int i){

        som.pegarArquivo(i);
        som.play();
    }
}