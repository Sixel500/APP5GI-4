package app6;

/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private AnalLex lexical;
  private Terminal terminalCourant;

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in) {
    Reader r = new Reader(in);
    this.lexical = new AnalLex((r.toString()));
    this.terminalCourant = this.lexical.prochainTerminal();
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
  return e();
}

  /** Méthode utilitaire pour vérifier et consommer le terminal attendu */
  private void terminal(Terminal.Type typeAttendu) {
    if (this.terminalCourant != null && this.terminalCourant.type == typeAttendu) {
      this.terminalCourant = this.lexical.prochainTerminal(); // On avance
    } else {
      ErreurSynt("Symbole inattendu. Attendu: " + typeAttendu);
    }
  }

    /** Règle T -> a  */
    private ElemAST t() {
        return new FeuilleAST(this.terminalCourant);
    }

    // Methode pour chaque symbole non-terminal de la grammaire retenue
    // ...
    // ...
    private ElemAST e() {
        ElemAST noeudGauche = t();
        String op = "";
        ElemAST noeudDroite = e();
        if (this.terminalCourant != null) {
            switch (this.terminalCourant.type) {
                case PLUS:
                    op = this.terminalCourant.chaine;
                    terminal(Terminal.Type.PLUS);
                    return new NoeudAST(op, noeudGauche, noeudDroite);
                case MOINS:
                    op = this.terminalCourant.chaine;
                    terminal(Terminal.Type.MOINS);
                    return new NoeudAST(op, noeudGauche, noeudDroite);
                case FOIS:
                    op = this.terminalCourant.chaine;
                    terminal(Terminal.Type.FOIS);
                    return new NoeudAST(op, noeudGauche, noeudDroite);
                case DIVISE:
                    op = this.terminalCourant.chaine;
                    terminal(Terminal.Type.DIVISE);
                    return new NoeudAST(op, noeudGauche, noeudDroite);
                default:
                    return noeudGauche;
            }
        }
        ErreurSynt("Terminal courant vide!");
        return null;
    }



/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
  System.err.println("Erreur Syntaxique : " + s);
  System.exit(1);
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

