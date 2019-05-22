import java.util.ArrayList;
/**
 * Write a description of class Rei here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rei extends Peca
{    
    private Player oposto;
    private Tabuleiro tabuleiro;
    public boolean roqueMenor = false;
    public boolean roqueMaior = false;
    /**
     * Constructor for objects of class Rei
     */
    public Rei(Casa casa, int tipo, Player player, Player oposto,Tabuleiro tabuleiro)
    {
        super(casa,tipo, player);
        this.oposto = oposto;
        this.tabuleiro = tabuleiro;
        jaMoveu = false;
    }

    @Override
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
     public void mover(Casa destino) {
        roqueMenor = roqueMenor();
        roqueMaior = roqueMaior();
        if((Math.abs(destino.getX() - this.x) == 2) && ((destino.getX() == 6 && destino.getY() == 0) || (destino.getX() == 6 && destino.getY() == 7))) {
            if(roqueMenor){
                Casa torreInicio = tabuleiro.getCasa(7,y);
                Casa torreFim = tabuleiro.getCasa(5,y);
                Peca peca = torreInicio.getPeca();
                torreInicio.removerPeca();
                torreFim.colocarPeca(peca);
                casa.removerPeca(); 
                destino.colocarPeca(this);
                casa = destino; 
                this.x = casa.getX();
                this.y = casa.getY();
                jaMoveu = true;
            }
            else{
                return;
            }

        }
        else if((Math.abs(destino.getX() - this.x) == 2) && ((destino.getX() == 2 && destino.getY() == 0) || (destino.getX() == 2 && destino.getY() == 7))) {
            if(roqueMaior){
                Casa torreInicio = tabuleiro.getCasa(0,y);
                Casa torreFim = tabuleiro.getCasa(3,y);
                Peca peca = torreInicio.getPeca();
                torreInicio.removerPeca();
                torreFim.colocarPeca(peca);
                casa.removerPeca(); 
                destino.colocarPeca(this);
                casa = destino; 
                this.x = casa.getX();
                this.y = casa.getY();
                jaMoveu = true;
            }
            else{
                return;
            }

        }
        
        else{
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

        }
    }

    @Override
    public ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro){
        ArrayList<Casa> movimentos = new ArrayList<Casa>();
        int cor = getCor(); //Pegar a cor

        movimentos.add(tabuleiro.getCasa(x,y+1)); //UP
        movimentos.add(tabuleiro.getCasa(x+1,y+1));
        movimentos.add(tabuleiro.getCasa(x-1,y+1));    

        movimentos.add(tabuleiro.getCasa(x,y-1)); //DOWN
        movimentos.add(tabuleiro.getCasa(x+1,y-1));
        movimentos.add(tabuleiro.getCasa(x-1,y-1));  

        movimentos.add(tabuleiro.getCasa(x+1,y)); //SIDES
        movimentos.add(tabuleiro.getCasa(x-1,y));        

        for(int i = movimentos.size()-1; i >= 0; i--){ //Removendo capturas inválidas, seja por possuir uma peça no destino, seja por não possuir uma peça entre o destino e a origem.
            Casa fim = movimentos.get(i);
            if(fim == null){
                movimentos.remove(i);
            }
            else{               
                Peca peca = fim.getPeca();
                if(peca != null){

                    if(peca.getCor() == cor){
                        movimentos.remove(i);
                    }

                }
            }

        }

        if(roqueMenor) movimentos.add(tabuleiro.getCasa(6,y));
        if(roqueMaior) movimentos.add(tabuleiro.getCasa(2,y));
        return movimentos;
    }

    public boolean roqueMenor(){
        if(this.jaMoveu == false){
            if(casa.casaAtacada(oposto,tabuleiro)){
                return false;
            }
            else{
                ArrayList<Casa> entreTorre = new ArrayList<Casa>();
                entreTorre.add(tabuleiro.getCasa(5,y));
                entreTorre.add(tabuleiro.getCasa(6,y));
                Peca peca = tabuleiro.getCasa(7,y).getPeca();
                if(!(peca instanceof Torre)){
                    return false;
                }
                else{
                    Torre torre = (Torre) peca;
                    if(torre.jaMoveu != false){  
                        return false;
                    }
                    else{
                        for(Casa cs : entreTorre){
                            if(cs.possuiPeca()) return false;
                            else if(cs.casaAtacada(oposto, tabuleiro)) return false;                            

                        }
                    }
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public boolean roqueMaior(){
        if(this.jaMoveu == false){
            if(casa.casaAtacada(oposto,tabuleiro)){
                return false;
            }
            else{
                ArrayList<Casa> entreTorre = new ArrayList<Casa>();
                entreTorre.add(tabuleiro.getCasa(3,y));
                entreTorre.add(tabuleiro.getCasa(2,y));
                entreTorre.add(tabuleiro.getCasa(1,y));                
                Peca peca = tabuleiro.getCasa(0,y).getPeca();
                if(!(peca instanceof Torre)){
                    return false;
                }
                else{
                    Torre torre = (Torre) peca;
                    if(torre.jaMoveu != false){  
                        return false;
                    }
                    else{
                        for(Casa cs : entreTorre){
                            if(cs.possuiPeca()) return false;
                            else if(cs.casaAtacada(oposto, tabuleiro)) return false;                            

                        }
                    }
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
}
