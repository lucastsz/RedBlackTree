package RedBlackTree;

public class RedBlackTree {
   
    private No raiz;
    private No RNULL;

    public RedBlackTree() {
        RNULL = new No();
        RNULL.cor = 0;
        RNULL.esquerda = null;
        RNULL.direita = null;
        raiz = RNULL;
    }

    private No min(No node) {
        while (node.esquerda != RNULL) {
            node = node.esquerda;
        }
        return node;
    }

    private No max(No node) {
        while (node.direita != RNULL) {
            node = node.direita;
        }
        return node;
    }

    private void rotacaoEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != RNULL) {
            y.esquerda.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(No x) {
        No y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != RNULL) {
            y.direita.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.direita) {
            x.pai.direita = y;
        } else {
            x.pai.esquerda = y;
        }
        y.direita = x;
        x.pai = y;
    }

    private void rubroNegraTroca(No u, No v) {
        if (u.pai == null) {
            raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        v.pai = u.pai;
    }

    private void corrigirExclusao(No x) {
        No s;
        while (x != raiz && x.cor == 0) {
            if (x == x.pai.esquerda) {
                s = x.pai.direita;
                if (s.cor == 1) {
                    s.cor = 0;
                    x.pai.cor = 1;
                    rotacaoEsquerda(x.pai);
                    s = x.pai.direita;
                }

                if (s.esquerda.cor == 0 && s.direita.cor == 0) {
                    s.cor = 1;
                    x = x.pai;
                } else {
                    if (s.direita.cor == 0) {
                        s.esquerda.cor = 0;
                        s.cor = 1;
                        rotacaoDireita(s);
                        s = x.pai.direita;
                    }

                    s.cor = x.pai.cor;
                    x.pai.cor = 0;
                    s.direita.cor = 0;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                s = x.pai.esquerda;
                if (s.cor == 1) {
                    s.cor = 0;
                    x.pai.cor = 1;
                    rotacaoDireita(x.pai);
                    s = x.pai.esquerda;
                }

                if (s.direita.cor == 0 && s.direita.cor == 0) {
                    s.cor = 1;
                    x = x.pai;
                } else {
                    if (s.esquerda.cor == 0) {
                        s.direita.cor = 0;
                        s.cor = 1;
                        rotacaoEsquerda(s);
                        s = x.pai.esquerda;
                    }

                    s.cor = x.pai.cor;
                    x.pai.cor = 0;
                    s.esquerda.cor = 0;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        x.cor = 0;
    }

    private void exclusao(No node, int chave) {
        No z = RNULL;
        No x, y;
        while (node != RNULL) {
            if (node.valor == chave) {
                z = node;
            }

            if (node.valor <= chave) {
                node = node.direita;
            } else {
                node = node.esquerda;
            }
        }

        if (z == RNULL) {
            System.out.println("Não foi possível encontrar a chave na árvore");
            return;
        }

        y = z;
        int yOriginalColor = y.cor;
        if (z.esquerda == RNULL) {
            x = z.direita;
            rubroNegraTroca(z, z.direita);
        } else if (z.direita == RNULL) {
            x = z.esquerda;
            rubroNegraTroca(z, z.esquerda);
        } else {
            y = min(z.direita);
            yOriginalColor = y.cor;
            x = y.direita;
            if (y.pai == z) {
                x.pai = y;
            } else {
                rubroNegraTroca(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            rubroNegraTroca(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }
        if (yOriginalColor == 0) {
            corrigirExclusao(x);
        }
    }

    public void exclusao(int valor) {
        exclusao(this.raiz, valor);
    }

    private void correcaoInsercao(No k) {
        No u;
        while (k.pai.cor == 1) {
            if (k.pai == k.pai.pai.direita) {
                u = k.pai.pai.esquerda;
                if (u.cor == 1) {
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.esquerda) {
                        k = k.pai;
                        rotacaoDireita(k);
                    }
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoEsquerda(k.pai.pai);
                }
            } else {
                u = k.pai.pai.direita;

                if (u.cor == 1) {
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoDireita(k.pai.pai);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.cor = 0;
    }

    public void insercao(int chave) {
        No node = new No();
        node.pai = null;
        node.valor = chave;
        node.esquerda = RNULL;
        node.direita = RNULL;
        node.cor = 1;

        No y = null;
        No x = this.raiz;

        while (x != RNULL) {
            y = x;
            if (node.valor < x.valor) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        node.pai = y;
        if (y == null) {
            raiz = node;
        } else if (node.valor < y.valor) {
            y.esquerda = node;
        } else {
            y.direita = node;
        }

        if (node.pai == null) {
            node.cor = 0;
            return;
        }

        if (node.pai.pai == null) {
            return;
        }

        correcaoInsercao(node);
    }

    private No busca(No node, int chave) {
        if (node == RNULL || chave == node.valor) {
            return node;
        }

        if (chave < node.valor) {
            return busca(node.esquerda, chave);
        }
        return busca(node.direita, chave);
    }

    public No busca(int k) {
        return busca(this.raiz, k);
    }

    private void imprimir(No raiz, String indent, boolean last) {
        if (raiz != RNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = raiz.cor == 1 ? "RED" : "BLACK";
            System.out.println(raiz.valor + "(" + sColor + ")");
            imprimir(raiz.esquerda, indent, false);
            imprimir(raiz.direita, indent, true);
        }
    }

        public void imprimir() {

          TreePrinter<No> p = new TreePrinter<>(n -> n.valor + "(" + n.Cor() + ")", n -> n.esquerda, n -> n.direita);
          p.setSquareBranches(false);
          p.printTree(raiz);

    }
}

