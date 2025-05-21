package gui.system;


//java.awt é um conjunto de classes do Java que permite criar interfaces gráficas
//java.swing

import gui.blocos.GerenciadorBlocos;
import gui.entidades.*;
import gui.eventos.ManipuladorDeEventos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class PainelJogo extends JPanel implements Runnable { //GamePanel herda de JPanel, que é a área onde o jogo vai ser desenhado
    //implements Runnable indica que essa classe será executada em uma Thread separada, permitindo que o jogo rode continuamente sem travar a interface


    // Atributos privados

    private final int tamanhoOriginalBloco = 16; //O tamanho de um bloco do jogo é 16 pixels
    private final int escala = 3; //O tamanho do bloco será escalada em 3 vezes, ficando com 48 pixels

    private final int tamanhoBloco = tamanhoOriginalBloco * escala;
    private final int tamanhoColuna = 16; //O jogo terá 16 colunas
    private final int tamanhoLinha = 12; //O jogo terá 12 linhas
    private final int telaLargura = tamanhoBloco * tamanhoColuna;
    private final int telaAltura = tamanhoBloco * tamanhoLinha;

    private final int linhaMundoMax = 50;
    private final int colMundoMax = 50;
    private final int mundoLargura = tamanhoBloco * linhaMundoMax;
    private final int mundoAltura = tamanhoBloco * colMundoMax;

    private String personagemSelecionado;
    private boolean jogoIniciado = false;

    private int FPS = 60;

    // Sistema

    private GerenciadorBlocos blocosG = new GerenciadorBlocos(this);//

    private EventosTeclado eventosTeclado = new EventosTeclado(this); //Cria um objeto que lida com os eventos do teclado.

    private ChecadorColisoes cColisoes = new ChecadorColisoes(this);

    private CriadorAtivos cAtivos = new CriadorAtivos(this);

    //private FerramentasUteis ferramentasUteis=new FerramentasUteis();

    private InterfaceUsuario iu = new InterfaceUsuario(this);

    private ManipuladorDeEventos manipuladorDeEventos=new ManipuladorDeEventos(this);

    Thread threadJogo; //Essa Thread permitirá que o jogo rode continuamente.

    // Entidade e objeto

    public Jogador jogador = new Jogador(this, eventosTeclado);
    private Entidade obj[] = new Entidade[100];
    private Entidade npc[] = new Entidade[100];
    private Entidade coelho[]=new Entidade[10];
    private Entidade criatura[]=new Entidade[10];
    private ArrayList<Entidade> entidadeLista= new ArrayList<>();
    private Entidade alimento[]=new Entidade[10];

   // public BlocoInterativo blocoInterativo[][]=new BlocoInterativo[mapaMax][50];


    // Estado do jogo

    private int estadoJogo;

    private final int estadoTitulo = 0;

    private final int estadoPlay = 1;
    private final int estadoPausa = 2;
    private final int estadoDialogo=4;
    private int estadoDescricao = 3;
    private int estadoPersonagem=5;
    private int estadoJogoFinalizado=6;

    // Mapa do jogo

    private final int mapaMax=10;
    private int mapaAtual=0;

    // Métodos de acesso getters

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

    public String getPersonagemSelecionado() {
        return personagemSelecionado;
    }
    public int getEstadoTitulo(){
        return estadoTitulo;
    }
    public GerenciadorBlocos getBlocosG() {
        return blocosG;
    }
    public Entidade[] getObj() {
        return obj;
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

    public final int getEstadoPlay() {
        return estadoPlay;
    }
    public final int getEstadoPausa() {
        return estadoPausa;
    }
    public Entidade[] getNpc() {
        return npc;
    }
    public ManipuladorDeEventos getManipuladorDeEventos(){
        return manipuladorDeEventos;
    }

    public Entidade[] getCriatura() {
        return criatura;
    }

    public int getEstadoDialogo() {
        return estadoDialogo;
    }
    public EventosTeclado getEventosTeclado(){
        return eventosTeclado;
    }

    public ArrayList<Entidade> getEntidadeList() {
        return entidadeLista;
    }

    public Entidade[] getAlimento(){
        return alimento;
    }

    public final int getMapaMax(){
        return mapaMax;
    }

    public int getMapaAtual() {
        return mapaAtual;
    }

    // Métodos setters

    public void setPersonagemSelecionado(String personagemSelecionado) {
        this.personagemSelecionado = personagemSelecionado;
    }
    public void setObj(Entidade[] obj) {
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

    public void setBlocosG(GerenciadorBlocos blocosG) {
        this.blocosG = blocosG;
    }
    public void setManipuladorDeEventos(){
        this.manipuladorDeEventos=manipuladorDeEventos;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setEntidadeLista(ArrayList<Entidade> entidadeLista) {
        this.entidadeLista = entidadeLista;
    }



    public PainelJogo() {


        Timer timer = new Timer(16, e -> repaint()); // 60 vezes por segundo
        timer.start();

        this.setPreferredSize(new Dimension(telaLargura, telaAltura)); //Define o tamanho da tela
        this.setBackground(Color.black); //Define o fundo como preto
        this.setDoubleBuffered(true); //Ativa o "Double Buffering"(Isso ajuda a evitar que a tela fique piscando ao desenhar elementos)
        this.addKeyListener(eventosTeclado); //Adiciona o KeyListener para capturar teclas pressionadas
        this.setFocusable(true); //Permite que o painel capture eventos de teclado
        this.requestFocus(); //Garante que o painel receba o foco do teclado:
        //this.ferramentasUteis=new FerramentasUteis();


    }

    // Colisao objetos

    public boolean tileColisao;

    public void iniciarJogo() {

        //Criar o personagem baseado na escolha do jogador
        if (personagemSelecionado.equals("médico")) {
            jogador = new Doctor(this, eventosTeclado);
        } else if (personagemSelecionado.equals("mecânico")) {
            jogador = new Mechanic(this, eventosTeclado);
        } else if (personagemSelecionado.equals("rastreador")) {
            jogador = new Detective(this, eventosTeclado);
        } else {
            jogador = new Survivor(this, eventosTeclado);
        }

        requestFocusInWindow();
        jogoIniciado = true;

        setupJogo(); // Adicionado para os objetos de cada personagem
        iniciarThreadJogo();
    }

    public void setupJogo() {

        cAtivos.setObjeto();
        cAtivos.setNPC();
        cAtivos.setCOELHO();
        cAtivos.setCriatura();


        estadoJogo=estadoTitulo;

    }
    public void iniciarThreadJogo() { //Cria e inicia a Thread do jogo, chamando o método run()
        threadJogo = new Thread(this);
        threadJogo.start();

    }
    @Override
    public void run() { // Loop principal do jogo (mantém o jogo rodando enquanto a threadJogo estiver ativa)

        double intervaloDesenho = 1000000000 / FPS; // Tempo ideal entre os quadros
        double delta = 0;
        long ultimoTempo = System.nanoTime();
        long tempoAtual;
        long cronometro = 0;
        int contadorQuadros = 0;

        while (threadJogo != null) {
            tempoAtual = System.nanoTime();

            delta += (tempoAtual - ultimoTempo) / intervaloDesenho;
            cronometro += (tempoAtual - ultimoTempo);

            ultimoTempo = tempoAtual;

            if (delta >= 1) { // Só atualiza o jogo quando delta ≥ 1
                update();    // Atualiza a lógica do jogo
                repaint();      // Requisição para redesenhar a tela
                delta--;        // Evita múltiplas atualizações por quadro
                contadorQuadros++;
            }

      /*      if (cronometro >= 1000000000) { // A cada 1 segundo...
                System.out.println("FPS: " + contadorQuadros);
                contadorQuadros = 0;
                cronometro = 0;
            }

       */

            try {
                Thread.sleep(1); // Pequena pausa para reduzir o uso da CPU
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        if (estadoJogo == estadoPlay) {

            jogador.update();


            // NPC
           for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // CRIATURA

          for (int i = 0; i < criatura.length; i++) {
                if (criatura[i] != null) {
                    criatura[i].update();
                }
            }


        }
        if (estadoJogo == estadoPausa) {
            // nada
        }
    }

    // Tela de clima

    // Atributos privados

    private ArrayList<Gota> gotas = new ArrayList<>();
    private boolean mostrarChuva = false;
    private ArrayList<FlocoDeNeve> flocos = new ArrayList<>();
    private boolean mostrarNevasca = false;
    private boolean mostrarEfeitoConfusao = false;
    private float angulo = 0;
    private Random random = new Random();

    // Métodos de acesso

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

        g2.setColor(new Color(255, 0, 0, 100));
        g2.fillRect(0, 0, largura, altura);

        angulo += 0.05f;
        if (angulo > 2 * Math.PI) angulo = 0;

        double tremor = Math.sin(System.currentTimeMillis() * 0.01) * 50;

        g2.setColor(new Color(255, 100, 100, 80));
        g2.rotate(-angulo * 1.5, largura / 2, altura / 2);
        g2.fillOval(largura / 2 - 100, altura / 2 - 100, 200, 200);
        g2.rotate(angulo * 1.5, largura / 2, altura / 2);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if (eventosTeclado.isChecarDesenhoTempo()) {
            drawStart = System.nanoTime();
        }

        // TELA DE TÍTULO
        if (estadoJogo == estadoTitulo) {
            iu.desenhar(g2);
        } else {

            // Desenhar blocos do mapa
            blocosG.draw(g2);

            // Adicionar entidades à lista de desenho
            entidadeLista.add(jogador);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entidadeLista.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entidadeLista.add(obj[i]);
                }
            }



           for (int i = 0; i < criatura.length; i++) {
                if (criatura[i] != null) {
                    entidadeLista.add(criatura[i]);
                }
            }







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


            // IU (Interface do Usuário)
            iu.desenhar(g2);

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
        }

        g2.dispose();
    }
}