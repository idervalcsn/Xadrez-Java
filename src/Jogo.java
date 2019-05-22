import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    public Player playerPreto;
    public Player playerBranco;
    public int turno = 1;
    public Jogo() {
        tabuleiro = new Tabuleiro();
        playerBranco = new Player(Player.JOGADOR_BRANCO);
        playerPreto = new Player(Player.JOGADOR_PRETO);
        criarPecas();
    }

    /**
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {

        //PEÇAS BRANCAS
        for(int i = 0; i < 8; i++){
            Casa casa = tabuleiro.getCasa(i,1);
            Peca peca = new Peao(casa, Peca.PEAO_BRANCO,playerBranco);

        }

        Casa casa1 = tabuleiro.getCasa(0,0);
        Peca peca1 = new Torre(casa1, Peca.TORRE_BRANCA,playerBranco);

        Casa casa2 = tabuleiro.getCasa(7,0);
        Peca peca2 = new Torre(casa2, Peca.TORRE_BRANCA,playerBranco);

        Casa casa3 = tabuleiro.getCasa(1,0);
        Peca peca3 = new Cavalo(casa3, Peca.CAVALO_BRANCO, playerBranco);

        Casa casa4 = tabuleiro.getCasa(6,0);
        Peca peca4 = new Cavalo(casa4, Peca.CAVALO_BRANCO, playerBranco);

        Casa casa5 = tabuleiro.getCasa(2,0);
        Peca peca5 = new Bispo(casa5, Peca.BISPO_BRANCO, playerBranco);

        Casa casa6 = tabuleiro.getCasa(5,0);
        Peca peca6 = new Bispo(casa6, Peca.BISPO_BRANCO, playerBranco);

        Casa casa7 = tabuleiro.getCasa(3,0);
        Peca peca7 = new Rainha(casa7, Peca.RAINHA_BRANCA, playerBranco);

        Casa casa8 = tabuleiro.getCasa(4,0);
        Peca peca8 = new Rei(casa8, Peca.REI_BRANCO, playerBranco,playerPreto,tabuleiro);

        //PEÇAS PRETAS
        for(int i = 0; i < 8; i++){
            Casa casa = tabuleiro.getCasa(i,6);
            Peca peca = new Peao(casa, Peca.PEAO_PRETO, playerPreto);

        }

        Casa casa9 = tabuleiro.getCasa(0,7);
        Peca peca9 = new Torre(casa9, Peca.TORRE_PRETA, playerPreto);

        Casa casa10 = tabuleiro.getCasa(7,7);
        Peca peca10 = new Torre(casa10, Peca.TORRE_PRETA, playerPreto);

        Casa casa11 = tabuleiro.getCasa(1,7);
        Peca peca11 = new Cavalo(casa11, Peca.CAVALO_PRETO, playerPreto);

        Casa casa12 = tabuleiro.getCasa(6,7);
        Peca peca12 = new Cavalo(casa12, Peca.CAVALO_PRETO, playerPreto);

        Casa casa13 = tabuleiro.getCasa(2,7);
        Peca peca13 = new Bispo(casa13, Peca.BISPO_PRETO, playerPreto);

        Casa casa14 = tabuleiro.getCasa(5,7);
        Peca peca14 = new Bispo(casa14, Peca.BISPO_PRETO, playerPreto);

        Casa casa15 = tabuleiro.getCasa(3,7);
        Peca peca15 = new Rainha(casa15, Peca.RAINHA_PRETA, playerPreto);

        Casa casa16 = tabuleiro.getCasa(4,7);
        Peca peca16 = new Rei(casa16, Peca.REI_PRETO, playerPreto,playerBranco,tabuleiro);
    }

    /**
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        Peca pecaDestino = destino.getPeca();
        int pecaTipo = peca.getTipo();
        boolean foiEmpassant = false; //Diz se a ultima jogada foi em passant

        int dify = Math.abs( destinoY-origemY);
        int difx = destinoX-origemX;

        if (peca.getCor() == turno && peca.movimentosLegais(tabuleiro).contains(destino)){ //Verifica se o movimento dado é legal 
            peca.mover(destino);
            //Ativar variável dos peões se a sua ultima jogada for de duas casas 
            if ((peca instanceof Peao) && (dify ==2)){
                for(Peca pc2 : playerPreto.getPecas()){
                    pc2.duasCasas = false;   
                }
                for(Peca pc2 : playerBranco.getPecas()){
                    pc2.duasCasas = false;   
                }
                peca.duasCasas = true; 

            }else{
                for(Peca pc2 : playerPreto.getPecas()){
                    pc2.duasCasas = false;   
                }
                for(Peca pc2 : playerBranco.getPecas()){
                    pc2.duasCasas = false;   
                }  

            }

            //Ver se a jogada foi emPassant
            if(((difx!=0)&&(dify!=0)&&(pecaDestino == null))&&(peca instanceof Peao)){
                foiEmpassant = true;   
            }
            //Colocando como nulo pro compilador não reclamar
            Player player15 = peca.getPlayer(); 
            //Peca pecaSafado = null;
            //Casa peaoSafado = null;
            Peca pecaMeio = null;
            Casa casaMeio = null;
            if(foiEmpassant == true){
                //Encontrar a peca no meio do em passant e remover
                casaMeio = tabuleiro.getCasa(origemX+difx,origemY);   
                pecaMeio = casaMeio.getPeca();
                player15.removerPeca(pecaMeio); 
                casaMeio.removerPeca();

            }

            //Verificar cheque
            if(cheque(turno) == true){

                System.out.println("Você está/estará em cheque");
                peca.mover(origem); //Voltar o movimento

                if(pecaDestino != null){
                    //Recuperar a peça inimiga que foi capturada
                    pecaDestino.mover(destino);
                    Player player = pecaDestino.getPlayer();
                    player.adicionarPeca(pecaDestino);
                    return;

                }
                if(foiEmpassant == true){
                    if (pecaTipo ==0|| pecaTipo ==6){
                        //Voltar o movimento da peça capturada do em passant
                        pecaMeio.mover(casaMeio);     
                    }
                }
            }
            else{

                //Checar promoção
                Peca destino2 = destino.getPeca();
                if (destino2!= null){
                    int tipo2 = destino2.getTipo();
                    if ((tipo2 == 6)||(tipo2 ==0)){ 
                        podePromover(destino2);
                    }
                }
                turno = turno*(-1); //Mudar o turno
                if(reiAfogado(turno)) { //Checar se o jogo foi afogado
                    System.out.println("Rei afogado");
                    return;
                }
                //Verificar cheque mate
                if(chequemate(turno)){
                    System.out.println("Cheque Mate");
                }       
                if(empatePorMaterial()){
                    System.out.println("Empate por falta de material");
                    return;
                }
            }
        }
    }

    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean cheque(int turno){ 
        if(turno == 1){ //Turno das brancas           
            Peca rei = playerBranco.getRei(); 
            Casa casa = rei.getCasa();
            //Vê se alguma peça preta ataca a casa do rei.
            for(Peca pc : playerPreto.getPecas()){
                if (pc.movimentosLegais(tabuleiro).contains(casa)){
                    return true;
                }
            }
        }

        else{             
            Peca rei = playerPreto.getRei();
            Casa casa = rei.getCasa();
            //Vê se alguma peça branca ataca a casa do rei.
            for(Peca pc : playerBranco.getPecas()){
                if (pc.movimentosLegais(tabuleiro).contains(casa)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chequemate(int turno){
        if(cheque(turno) == false) return false; //Um jogador que não está em cheque não pode sofrer chequemate.
        if(turno == 1){
            
            for(Peca pc : playerBranco.getPecas()){ //Itera sobre todas as peças no tabuleiro, para saber se alguma evita o chequemate.
                ArrayList<Casa> movimentos = pc.movimentosLegais(tabuleiro);
                for (int i = 0; i < movimentos.size();i++){
                    Casa fim = movimentos.get(i); //Casa destino hipotética
                    Casa origem = pc.getCasa(); //Casa de início
                    Peca pecaFim = fim.getPeca(); //Caso exista, a peça no destino hipotético
                    boolean tmp = pc.jaMoveu;  
                    pc.mover(fim); 
                    if(cheque(turno) == true){ //Verifica se a jogada retirou o cheque
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){ 
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player preto = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))preto.adicionarPeca(pecaFim); //Como o rei nunca é retirado da lista de peças, ele não precisa ser readicionado.                             

                        }
                    }
                    else{ //Caso alguma jogada retire o cheque, não temos o chequemate, e retornaremos falso.
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player preto = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))preto.adicionarPeca(pecaFim);    
                        }

                        return false;
                    }
                }

            }
            return true;
        }
        else if(turno == -1){

            for(Peca pc : playerPreto.getPecas()){ //Itera sobre todas as peças no tabuleiro, para saber se alguma evita o chequemate
                ArrayList<Casa> movimentos = pc.movimentosLegais(tabuleiro);
                for (int i = 0; i < movimentos.size();i++){
                    Casa fim = movimentos.get(i); //Casa destino hipotética
                    Casa origem = pc.getCasa(); //Casa de início
                    Peca pecaFim = fim.getPeca(); //Caso exista, a peça no destino hipotético
                    boolean tmp = pc.jaMoveu;  

                    pc.mover(fim); 
                    if(cheque(turno) == true){//Verifica se a jogada retirou o cheque
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player branco = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))branco.adicionarPeca(pecaFim);//Como o rei nunca é retirado da lista de peças, ele não precisa ser readicionado.                             
                            

                        }
                    }
                    else{//Caso alguma jogada retire o cheque, não temos o chequemate, e retornaremos falso.
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player branco = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))branco.adicionarPeca(pecaFim);    
                        }

                        return false;
                    }
                }

            }
            return true;
        }
        return false;
    }

    public void podePromover(Peca peao){    
        int cor = peao.getCor(); //1 = branco -1 = preto
        Casa casaPeao = peao.getCasa();
        int yPeao = casaPeao.getY();

        if (((cor == 1)&&(yPeao==7)) ||((cor==-1)&&(yPeao==0))){//Posição e cores corretas pra promoção 
            casaPeao.removerPeca();
            peao.getPlayer().removerPeca(peao);

            String novaPeca;
            boolean valorValido = false;
            novaPeca = JOptionPane.showInputDialog("Rainha=0  Torre=1  Bispo=2  Cavalo=3");
            while(valorValido ==false){ //Ficar repetindo até colocar valor vlaido
                if (novaPeca.contains("0")){
                    if(cor ==1){
                        Peca pecaPromovida = new Rainha(casaPeao,Peca.RAINHA_BRANCA,playerBranco);
                    }else{
                        Peca pecaPromovida = new Rainha(casaPeao,Peca.RAINHA_PRETA,playerPreto);
                    }
                    valorValido = true;
                }else if (novaPeca.contains("1")){
                    if(cor ==1){
                        Peca pecaPromovida = new Torre(casaPeao,Peca.TORRE_BRANCA,playerBranco);
                    }else{
                        Peca pecaPromovida = new Torre(casaPeao,Peca.TORRE_PRETA,playerPreto);
                    }
                    valorValido = true;
                }else if (novaPeca.contains("2")){
                    if(cor ==1){Peca pecaPromovida = new Bispo(casaPeao,Peca.BISPO_BRANCO,playerBranco);
                        valorValido = true;
                    }else{
                        Peca pecaPromovida = new Bispo(casaPeao,Peca.BISPO_PRETO,playerPreto);
                    }
                }else if (novaPeca.contains("3")){
                    if(cor ==1){
                        Peca pecaPromovida = new Cavalo(casaPeao,Peca.CAVALO_BRANCO,playerBranco);
                    }else{
                        Peca pecaPromovida = new Cavalo(casaPeao,Peca.CAVALO_PRETO,playerPreto);
                    }
                    valorValido = true;
                }else{
                    novaPeca = JOptionPane.showInputDialog("Rainha=0  Torre=1  Bispo=2  Cavalo=3 *VALOR INVALIDO");    
                }
            }
        }
    }

    public boolean reiAfogado(int turno){ 
        if(cheque(turno)) return false;
        if(turno == 1){

            for(Peca pc : playerBranco.getPecas()){ //Itera sobre todas as peças no tabuleiro, para saber se alguma evita o chequemate
                ArrayList<Casa> movimentos = pc.movimentosLegais(tabuleiro);
                for (int i = movimentos.size() - 1; i >= 0 ;i--){
                    Casa fim = movimentos.get(i); //Casa destino hipotética
                    Casa origem = pc.getCasa(); //Casa de início
                    Peca pecaFim = fim.getPeca(); //Caso exista, a peça no destino hipotético
                    boolean tmp = pc.jaMoveu;  

                    pc.mover(fim); 
                    if(cheque(turno) == true){
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        movimentos.remove(fim);
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player preto = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))preto.adicionarPeca(pecaFim);                            

                        }
                    }
                    else{
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player preto = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))preto.adicionarPeca(pecaFim);    
                        }

                        return false;
                    }
                }
                if(!movimentos.isEmpty()){
                    return false;
                }
            }
            return true;
        }

        else if(turno == -1){

            for(Peca pc : playerPreto.getPecas()){ //Itera sobre todas as peças no tabuleiro, para saber se alguma evita o chequemate
                ArrayList<Casa> movimentos = pc.movimentosLegais(tabuleiro);
                for (int i = movimentos.size() - 1; i >= 0 ;i--){
                    Casa fim = movimentos.get(i); //Casa destino hipotética
                    Casa origem = pc.getCasa(); //Casa de início
                    Peca pecaFim = fim.getPeca(); //Caso exista, a peça no destino hipotético
                    boolean tmp = pc.jaMoveu;  

                    pc.mover(fim); 
                    if(cheque(turno) == true){
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        movimentos.remove(fim);
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player branco = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))branco.adicionarPeca(pecaFim);                            

                        }
                    }
                    else{
                        pc.mover(origem);
                        pc.jaMoveu = tmp;
                        if(pecaFim != null){
                            boolean tmpFim = pecaFim.jaMoveu;
                            pecaFim.mover(fim);
                            pecaFim.jaMoveu = tmpFim;
                            Player branco = pecaFim.getPlayer();
                            if(!(pecaFim instanceof Rei))branco.adicionarPeca(pecaFim);    
                        }

                        return false;
                    }
                }
                if(!movimentos.isEmpty()){
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public boolean empatePorMaterial(){
        ArrayList<Peca> brancoPecas = playerBranco.getPecas();
        ArrayList<Peca> pretoPecas = playerPreto.getPecas();    
        if(brancoPecas.size() == 1 && pretoPecas.size() == 1){
            return true;
        }
        else if(brancoPecas.size() == 1 && pretoPecas.size() == 2){
            
            for(int i = 0; i < 2; i++){
                if(pretoPecas.get(i) instanceof Cavalo || pretoPecas.get(i) instanceof Bispo){
                    return true;
                }
            }
        }
        else if(brancoPecas.size() == 2 && pretoPecas.size() == 1){
            for(int i = 0; i < 2; i++){
                if(brancoPecas.get(i) instanceof Cavalo || brancoPecas.get(i) instanceof Bispo){
                    return true;
                }
            }
        }

        
        return false;
    }
}
