
import javax.swing.JOptionPane;

public class ArvoreBinaria {

    private static BIntNo raiz;

    public ArvoreBinaria() {

        raiz = null;

    }

    private void visita(BIntNo no) {
        System.out.print(no.valor + " ");
    }

    public static int menu() {

        String msg = " ";
        int op;

        msg = msg + "Digite 1 para Em Ordem\n";
        msg = msg + "Digite 2 para Pre Ordem\n";
        msg = msg + "Digite 3 para Pos Ordem\n";
        msg = msg + "Digite 0 para sair do sistema\n";

        op = Integer.parseInt(JOptionPane.showInputDialog(msg));

        return op;
    }

    private BIntNo inserir(BIntNo arvore, int novoNo) {

        if (arvore == null) {

            return new BIntNo(novoNo);

        } else {

            if (novoNo < arvore.valor) {
                arvore.esq = inserir(arvore.esq, novoNo);
            } else {
                arvore.dir = inserir(arvore.dir, novoNo);
            }

            return arvore;

        }

    }

    public void inserirNo(int novoValor) {

        raiz = inserir(raiz, novoValor);

    }

    private void exibirEsquerdo(BIntNo arv) {

        if (arv != null) {

            exibirEsquerdo(arv.esq);

            System.out.print(arv.valor + " ");

        }

    }

    public void exibirNoEsq() {

        exibirEsquerdo(raiz);

    }

    private void exibirDireito(BIntNo arv) {

        if (arv != null) {

            exibirDireito(arv.dir);

            System.out.print(arv.valor + " ");

        }

    }

    public void exibirNoDir() {

        exibirDireito(raiz);

    }

    public void exibirRaiz() {

        System.out.println("Raiz: " + raiz.valor);

    }

    private BIntNo procurar(BIntNo tempNo, int item) {

        BIntNo pai = null;

        BIntNo filho = tempNo;

        while (filho != null && filho.valor != item) {

            pai = filho;

            if (item < filho.valor) {
                filho = filho.esq;
            } else {
                filho = filho.dir;
            }

        }

        if (filho == null) {

            System.out.println("Item não localizado!");

            return null;

        }

        if (pai == null) {

            if (tempNo.dir == null) {

                raiz = tempNo.esq;

            } else if (tempNo.esq == null) {

                raiz = tempNo.dir;

            } else {

                BIntNo temp = tempNo;

                BIntNo filhoTemp = tempNo.esq;

                while (filhoTemp.dir != null) {

                    temp = filhoTemp;

                    filhoTemp = filhoTemp.dir;

                }

                if (filhoTemp != tempNo.esq) {

                    temp.dir = filhoTemp.esq;

                    filhoTemp.esq = raiz.esq;

                }

                filhoTemp.dir = raiz.dir;

                raiz = filhoTemp;

            }

        } else {

            if (tempNo.dir == null) {

                if (pai.esq == tempNo) {
                    pai.esq = tempNo.esq;
                } else {
                    pai.dir = tempNo.esq;
                }

            } else {

                if (tempNo.esq == null) {

                    if (pai.esq == tempNo) {
                        pai.esq = tempNo.dir;
                    } else {
                        pai.dir = tempNo.dir;
                    }

                } else {

                    BIntNo temp = tempNo;

                    BIntNo filhoTemp = tempNo.esq;

                    while (filhoTemp.dir != null) {

                        temp = filhoTemp;

                        filhoTemp = filhoTemp.dir;

                    }

                    if (filhoTemp != tempNo.esq) {

                        temp.dir = filhoTemp.esq;

                        filhoTemp.esq = tempNo.esq;

                    }

                    filhoTemp.dir = tempNo.dir;

                    if (pai.esq == tempNo) {
                        pai.esq = filhoTemp;
                    } else {
                        pai.dir = filhoTemp;
                    }

                }

            }

        }

        return raiz;

    }

    public void remover(int item) {

        raiz = procurar(raiz, item);

    }

    public void exibirNo() {

        exibirNoEsq();

        exibirRaiz();

        exibirNoDir();

    }

    public void emOrdem(BIntNo ABB) {
        if (ABB != null) {

            emOrdem(ABB.esq);
            visita(ABB);
            emOrdem(ABB.dir);
        }

    }

    public void preOrdem(BIntNo ABB) {
        if (ABB != null) {

            visita(ABB);
            preOrdem(ABB.esq);
            preOrdem(ABB.dir);
        }

    }

    public void posOrdem(BIntNo ABB) {
        if (ABB != null) {

            posOrdem(ABB.esq);
            posOrdem(ABB.dir);
            visita(ABB);
        }

    }

    public static void main(String[] args) {

      ArvoreBinaria ABB = new ArvoreBinaria();
  
      int op;
  
      do {
          op = menu();
  
          switch (op) {
              case 1:
                  System.out.println("Percurso em ordem:");
                  ABB.emOrdem(raiz);
                  System.out.println();
                  break;
  
              case 2: 
                  System.out.println("Percurso pré-ordem:");
                  ABB.preOrdem(raiz);
                  System.out.println();
                  break;
  
              case 3: 
                  System.out.println("Percurso pós-ordem:");
                  ABB.posOrdem(raiz);
                  System.out.println();
                  break;
  
              case 0: 
                  System.out.println("Saindo...");
                  break;
  
              default:
                  System.out.println("Opção inválida! Tente novamente.");
          }
  
          if (op != 0) {
              
              int valor = Integer.parseInt(JOptionPane.showInputDialog("Insira um valor na árvore:"));
              ABB.inserirNo(valor);
          }
  
      } while (op != 0);
  
      System.exit(0);
  }
}