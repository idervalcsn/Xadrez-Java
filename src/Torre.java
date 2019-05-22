import java.util.ArrayList;
/**
 * Write a description of class Torre here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Torre extends Peca 
{
    
    
    /**
     * Constructor for objects of class Torre
     */
    public Torre(Casa casa, int tipo, Player player)
    {
        super(casa,tipo, player);        
    }
    
    @Override
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
    }
    
     @Override
    public ArrayList<Casa> movimentosLegais(Tabuleiro tabuleiro){
        ArrayList<Casa> movimentos = new ArrayList<Casa>();
        int cor = getCor(); //Pegar a cor
        adicionarMovimentosRetos(tabuleiro,movimentos,0,1);//Cima
        adicionarMovimentosRetos(tabuleiro,movimentos,0,-1);//Baixo
        adicionarMovimentosRetos(tabuleiro,movimentos,-1,0);//Esquerda
        adicionarMovimentosRetos(tabuleiro,movimentos,1,0);//Direita
        
        return movimentos;
    }
    
    //Movimento Horizontal até encontrar peça no meio do caminho     
    public void adicionarMovimentosRetos(Tabuleiro tabuleiro,ArrayList<Casa> movimentos, int deslocax,int deslocay){ 
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
