public class ArvoreAVL {

    Elemento raiz;

    public void inserir(int valor) {
        Elemento novo = new Elemento(valor);
        inserirRecursivo(raiz, novo);
        balancear(raiz);
    }

    private void inserirRecursivo(Elemento atual, Elemento novo) {
        if(atual==null) {
            raiz = novo;
        } else {
            if(novo.valor < atual.valor){
                if(atual.esquerda == null) {
                    atual.esquerda = novo;
                } else {
                    inserirRecursivo(atual.esquerda, novo);
                }
            } else {
                if(atual.direita == null) {
                    atual.direita = novo;
                } else {
                    inserirRecursivo(atual.direita, novo);
                }
            }
        }
    }

    public void preOrdem() {
        preOrdemRecursivo(raiz);
        System.out.println();
    }

    private void preOrdemRecursivo(Elemento atual) {
        if (atual != null) {
            System.out.println(atual.valor + ", " + atual.bal);
            preOrdemRecursivo(atual.esquerda);
            preOrdemRecursivo(atual.direita);
        }
    }

    public void emOrdem() {
        emOrdemRecursivo(raiz);
        System.out.println();
    }

    private void emOrdemRecursivo(Elemento atual) {
        if (atual != null) {
            emOrdemRecursivo(atual.esquerda);
            System.out.println(atual.valor + ", " + atual.bal);
            emOrdemRecursivo(atual.direita);
        }
    }

    public void posOrdem() {
        posOrdemRecursivo(raiz);
        System.out.println();
    }

    private void posOrdemRecursivo(Elemento atual) {
        if (atual != null) {
            posOrdemRecursivo(atual.esquerda);
            posOrdemRecursivo(atual.direita);
            System.out.println(atual.valor + ", " + atual.bal);
        }
    }

    public void remover(int valor) {
        if (raiz == null) {
            System.out.println("A arvore está vazia");
        } else {
            if(!buscar(valor)) {
                System.out.println("Esse valor não existe.");
                return;
            }
            if (valor == raiz.valor) {
                if (raiz.esquerda == null && raiz.direita == null) {
                    raiz = null;
                }
            }
            Elemento atual = raiz;
            Elemento paiAtual = null;
            while (atual != null) {
                if (valor < atual.valor) {
                    paiAtual = atual;
                    atual = atual.esquerda;
                } else if (valor > atual.valor) {
                    paiAtual = atual;
                    atual = atual.direita;
                }
                if (valor == atual.valor) {
                    break;
                }
            }
            if (atual != null) {
                if (atual.esquerda != null && atual.direita != null) {
                    Elemento sub = atual.esquerda;
                    Elemento paiSub = atual;
                    while (sub.direita != null) {
                        paiSub = sub;
                        sub = sub.direita;
                    }

                    sub.direita = atual.direita;
                    if (paiAtual != null) {
                        if (atual.valor < paiAtual.valor) {
                            paiAtual.esquerda = sub;
                        } else {
                            paiAtual.direita = sub;
                        }
                    }
                    paiSub.direita = null;
                    if (atual.esquerda != sub) {
                        sub.esquerda = atual.esquerda;
                    }

                    if (atual == raiz) {
                        raiz = sub;
                    }
                } else if (atual.esquerda != null) {
                    if (paiAtual != null) {
                        if (atual.valor < paiAtual.valor) {
                            paiAtual.esquerda = atual.esquerda;
                        } else {
                            paiAtual.direita = atual.esquerda;
                        }
                    } else { // atual é a raiz
                        raiz = raiz.esquerda;
                    }
                } else if (atual.direita != null) {
                    if (paiAtual != null) {
                        if (atual.valor < paiAtual.valor) {
                            paiAtual.esquerda = atual.direita;
                        } else {
                            paiAtual.direita = atual.direita;
                        }
                    } else { // atual é a raiz
                        raiz = raiz.direita;
                    }
                } else {
                    if (atual.valor < paiAtual.valor) {
                        paiAtual.esquerda = null;
                    } else {
                        paiAtual.direita = null;
                    }
                }
            }
            balancear(raiz);

        }
    }

    public boolean buscar(int valor) {
        if(raiz==null) {
            return false;
        }
        if (raiz.valor == valor) {
            return true;
        }
        Elemento aux = raiz;
        while (aux!=null) {
            if (aux.valor == valor) {
                return true;
            }
            if(valor < aux.valor) {
                aux = aux.esquerda;
            } else {
                aux = aux.direita;
            }
        }
        return false;
    }

    private int definirAltura(Elemento atual) {
        if (atual == null) {
            return -1;
        }

        int alturaEsquerda = definirAltura(atual.esquerda);
        int alturaDireita = definirAltura(atual.direita);

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    private void definirBal(Elemento atual) {
        atual.bal = definirAltura(atual.esquerda) - definirAltura(atual.direita);

        if (atual.direita != null) {
            definirBal(atual.direita);
        }
        if (atual.esquerda != null) {
            definirBal(atual.esquerda);
        }
    }

    private void balancear(Elemento atual) {
        definirBal(raiz);

        Elemento aux = raiz;
        Elemento paiAtual = null;

        while (aux != null) {
            if (atual.valor < aux.valor) {
                paiAtual = aux;
                aux = aux.esquerda;
            } else if (atual.valor > aux.valor) {
                paiAtual = aux;
                aux = aux.direita;
            }
            if (atual == aux) {
                break;
            }
        }
        if (atual.bal >= 2) {
            if(atual.esquerda.bal>=0) {
                rotacaoDireita(atual, paiAtual);
            } else {
                rotacaoDuplaDireita(atual, paiAtual);
            }
        }
        if(atual.bal <= -2){
            if(atual.direita.bal<=0) {
                rotacaoEsquerda(atual, paiAtual);
            } else {
                rotacaoDuplaEsquerda(atual, paiAtual);
            }
        }

        if (atual.direita != null) {
            balancear(atual.direita);
        }
        if (atual.esquerda != null) {
            balancear(atual.esquerda);
        }

    }

    private void rotacaoDireita(Elemento atual, Elemento pai) {
        Elemento auxAtual = atual;
        Elemento auxAtualFilho = atual.esquerda;

        auxAtual.esquerda = auxAtualFilho.direita;
        auxAtualFilho.direita = auxAtual;

        if (pai != null) {
            if (atual.valor < pai.valor) {
                pai.esquerda = auxAtualFilho;
            } else {
                pai.direita = auxAtualFilho;
            }
        } else {
            raiz = auxAtualFilho;
        }

        definirBal(raiz);
    }

    private void rotacaoEsquerda(Elemento atual, Elemento pai) {
        Elemento auxPai = atual;
        Elemento auxFilho = atual.direita;

        auxPai.direita = auxFilho.esquerda;
        auxFilho.esquerda = auxPai;

        if (pai != null) {
            if (atual.valor < pai.valor) {
                pai.esquerda = auxFilho;
            } else {
                pai.direita = auxFilho;
            }
        } else {
            raiz = auxFilho;
        }

        definirBal(raiz);
    }

    private void rotacaoDuplaDireita(Elemento atual, Elemento pai) {
        rotacaoEsquerda(atual.esquerda, atual);
        rotacaoDireita(atual, pai);
    }

    private void rotacaoDuplaEsquerda(Elemento atual, Elemento pai) {
        rotacaoDireita(atual.direita, atual);
        rotacaoEsquerda(atual, pai);
    }
}