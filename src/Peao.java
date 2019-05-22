import java.util.ArrayList;
/**
 * Write a description of class Peao here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Peao extends Peca
{

    /**
     * Constructor for objects of class Peao
     */
    public Peao(Casa casa, int tipo, Player player)
    {
        super(casa,tipo,player);

    }

    @Override
    public ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro){
        ArrayList<Casa> movimentos = new ArrayList<Casa>();
        int cor = getCor(); //Pegar a cor

        if(cor == 1){ //Movimentos do peao branco           
            movimentos.add(tabuleiro.getCasa(x,y+1)); //Andar pra frente
            movimentos.add(tabuleiro.getCasa(x+1,y+1)); //Diagonal superior direita
            movimentos.add(tabuleiro.getCasa(x-1,y+1)); //Diagonal superior esquerda    
            if(y == 1 && (!tabuleiro.getCasa(x,y+1).possuiPeca() ) ){ //Andar duas casas
                movimentos.add(tabuleiro.getCasa(x,y+2)); 
            }

        }
        else{ //Movimentos do peao preto
            movimentos.add(tabuleiro.getCasa(x,y-1)); //Andar pra frente
            movimentos.add(tabuleiro.getCasa(x+1,y-1));// Diagonal superior direita
            movimentos.add(tabuleiro.getCasa(x-1,y-1));// Diagonal superior esquerda     
            if(y == 6 && (!tabuleiro.getCasa(x,y-1).possuiPeca() ) ){ //Andar duas casas
                movimentos.add(tabuleiro.getCasa(x,y-2));
            }
        }

        for(int i = movimentos.size()-1; i >= 0; i--){ //Remover capturas invalidas.
            Casa fim = movimentos.get(i);
            if(fim == null){
                movimentos.remove(i); //Remover se a casa não existir
            }
            else{
                int deslocax = Math.abs(x - fim.getX());  //Quanto andou em x
                int deslocay = Math.abs(y - fim.getY());  //Quanto andou em y

                if(deslocax == deslocay){ //Andando na diagonal
                    Peca peca = fim.getPeca();
                    if(peca == null){
                        //Checar em passant
                        int difx = fim.getX()-x; //Diferença em relação ao X só que com direção (sem o modulo)
                        
                           
                          Casa  casaMeio= tabuleiro.getCasa(x+difx,y);//Peao no caminho
                        if (casaMeio!=null){  //Ver se casa existe
                            if (casaMeio.possuiPeca()){ //Ver se possui peça no caminho
                                Peca pecaMeio = casaMeio.getPeca(); 
                                 
                                
                                if(!((pecaMeio instanceof Peao)&& (pecaMeio.duasCasas ==true))){//Ver se a pessa no caminho é um peao preto
                                movimentos.remove(i);
                                }

                            }else{ //Se tiver peca no caminho
                                movimentos.remove(i);    
                            }
                        }else{ //Se tiver fora do tabuleiro
                            movimentos.remove(i);    
                        }
                    }
                    else if(peca.getPlayer().getCor() == cor){
                        movimentos.remove(i);   
                    }
                }

                else if(fim.possuiPeca()){
                    movimentos.remove(i);                
                }

            }
        }
        return movimentos;
    }

}
