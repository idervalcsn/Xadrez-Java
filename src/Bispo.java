import java.util.ArrayList;
/**
 * Write a description of class Bispo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bispo extends Peca 
{

    /**
     * Constructor for objects of class Bispo
     */
    public Bispo(Casa casa,int tipo, Player player)
    {
        super(casa,tipo,player);
    }

    
    @Override
    public ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro){ //Colocar diagonais da vizinhança
        ArrayList<Casa> movimentos = new ArrayList<Casa>();
        int cor = getCor(); //Pegar a cor
        adicionarMovimentosDiagonais(tabuleiro,movimentos,1,1);//Nordeste
        adicionarMovimentosDiagonais(tabuleiro,movimentos,1,-1);//Sudeste
        adicionarMovimentosDiagonais(tabuleiro,movimentos,-1,1);//Noroeste
        adicionarMovimentosDiagonais(tabuleiro,movimentos,-1,-1);//Sudoeste
        return movimentos;
    }
    // Checar diagonal até encontra peça no caminho
    public void adicionarMovimentosDiagonais(Tabuleiro tabuleiro,ArrayList<Casa> movimentos, int deslocax,int deslocay){
        Casa casa = tabuleiro.getCasa(x+deslocax,y+deslocay);
        while(casa != null){
            int casax = casa.getX();
            int casay = casa.getY();
            Peca peca = casa.getPeca();
            if(peca == null ){
                movimentos.add(casa);
            }
            else if (peca.getCor() != getCor() ){
                movimentos.add(casa); 
                break;
            }
            else{
                break; 
            }
            casa = tabuleiro.getCasa(casax+deslocax,casay+deslocay);
        }

    }

}
