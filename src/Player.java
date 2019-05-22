import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    public static final int JOGADOR_BRANCO = 1;
    public static final int JOGADOR_PRETO = -1;
    private int numDePecas;
    private int cor;
    private ArrayList<Peca> pecas;

    /**
     * Constructor for objects of class Player
     */
    public Player(int cor)
    {
        this.cor = cor;
        this.numDePecas = 0;
        pecas = new ArrayList<Peca>();
    }

    
    public void adicionarPeca(Peca peca)
    {

        pecas.add(peca);     
        numDePecas++;

    }

    public void removerPeca(Peca peca)
    {
        if(!(peca instanceof Rei)){
            pecas.remove(peca);
            numDePecas--;
        }
    }

    public int getCor(){
        return cor;
    }

    public Peca getRei(){

        for(Peca peca: pecas){
            if( peca instanceof Rei){
                return peca;
            }
        }
        return null;
    }

    public ArrayList<Peca> getPecas(){
        return pecas;
    }
}
