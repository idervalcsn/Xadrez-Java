import java.util.ArrayList;
/**
 * Write a description of class Cavalo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cavalo extends Peca
{
   

    /**
     * Constructor for objects of class Cavalo
     */
    public Cavalo(Casa casa, int tipo, Player player)
    {
        super(casa,tipo,player);
    }
        
    
    @Override
    public ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro){
        ArrayList<Casa> movimentos = new ArrayList<Casa>();
        int cor = getCor(); //Pegar a cor

                    
            movimentos.add(tabuleiro.getCasa(x+2,y+1)); //Horizontal --> Vertical
            movimentos.add(tabuleiro.getCasa(x+2,y-1));
            movimentos.add(tabuleiro.getCasa(x-2,y+1));
            movimentos.add(tabuleiro.getCasa(x-2,y-1));

            movimentos.add(tabuleiro.getCasa(x+1,y+2)); //Horizontal --> Vertical
            movimentos.add(tabuleiro.getCasa(x-1,y+2));
            movimentos.add(tabuleiro.getCasa(x+1,y-2));
            movimentos.add(tabuleiro.getCasa(x-1,y-2));
        

        for(int i = movimentos.size()-1; i >= 0; i--){ //Removendo capturas inválidas, seja por possuir uma peça no destino, seja por não possuir uma peça entre o destino e a origem.
            Casa fim = movimentos.get(i);
            if(fim == null){
                movimentos.remove(i);
            }
            else{               
                Peca peca = fim.getPeca();
                if(peca != null){

                    if(peca.getPlayer().getCor() == cor){
                        movimentos.remove(i);
                    }

                }
            }
            
        }
        return movimentos;
    }

   
}
