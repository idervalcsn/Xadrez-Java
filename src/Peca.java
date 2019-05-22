import java.util.ArrayList;
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public abstract class Peca {

    public static final int PEAO_BRANCO = 0;
    public static final int REI_BRANCO = 1;
    public static final int RAINHA_BRANCA = 2;
    public static final int CAVALO_BRANCO = 3;
    public static final int BISPO_BRANCO = 4;
    public static final int TORRE_BRANCA = 5;

    public static final int PEAO_PRETO = 6;
    public static final int REI_PRETO = 7;
    public static final int RAINHA_PRETA = 8;
    public static final int CAVALO_PRETO = 9;
    public static final int BISPO_PRETO = 10;
    public static final int TORRE_PRETA = 11;
    
    public boolean jaMoveu;
    public boolean duasCasas = false;
    
    protected Casa casa;
    protected int tipo;
    protected int x;
    protected int y;
    protected Player player;
    
    public Peca(Casa casa, int tipo, Player player) {
        this.casa = casa;
        this.tipo = tipo;
        this.player = player;        
        this.x = casa.getX();
        this.y = casa.getY();
        this.jaMoveu = false;
        casa.colocarPeca(this);
        player.adicionarPeca(this);
    }

    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Casa destino) {        
        if(destino.possuiPeca()){
            Peca peca = destino.getPeca();
            Player player = peca.getPlayer();
            player.removerPeca(peca);
            destino.removerPeca();
        }
        casa.removerPeca();
        destino.colocarPeca(this);
        casa = destino; 
        this.x = casa.getX();
        this.y = casa.getY();
        jaMoveu = true;
        
        Peca peca = player.getRei(); 
        Rei rei = (Rei) peca;        
        rei.roqueMenor = rei.roqueMenor(); 
        rei.roqueMaior = rei.roqueMaior();
 
    }

    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }

    public Player getPlayer() {
        return player;
    }   
    
    public int getCor(){
        return player.getCor();
    }
    
    public Casa getCasa(){
     return casa;   
    }

     public abstract ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro); 
}