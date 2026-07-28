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

    private ElemAST e(){
        ElemAST noeudGauche = t();

        if (this.terminalCourant != null && (this.terminalCourant.type == Terminal.Type.PLUS || terminalCourant.type == Terminal.Type.MOINS)){
            String op = this.terminalCourant.chaine;
            terminal(terminalCourant.type);
            ElemAST noeudDroit = e();
            return new NoeudAST(op, noeudGauche, noeudDroit);
        }
        return noeudGauche;
    }
    private ElemAST t(){
        ElemAST noeudGauche = f();

        if (this.terminalCourant != null && (this.terminalCourant.type == Terminal.Type.FOIS || terminalCourant.type == Terminal.Type.DIVISE)){
            String op = this.terminalCourant.chaine;
            terminal(terminalCourant.type);
            ElemAST noeudDroit = t();
            return new NoeudAST(op, noeudGauche, noeudDroit);
        }
        return noeudGauche;
    }


    private ElemAST f() {
        Terminal temp = terminalCourant;
        if (terminalCourant.type == Terminal.Type.NOMBRE) {
            terminal(Terminal.Type.NOMBRE);
            return new FeuilleAST(temp); // On passe le terminal directement
        }
        else if (terminalCourant.type == Terminal.Type.OPERANDE) {
            terminal(Terminal.Type.OPERANDE);
            return new FeuilleAST(temp); // On garde l'opérande
        }
        else if (terminalCourant.type == Terminal.Type.PARENTHESE) {
            terminal(Terminal.Type.PARENTHESE);
            ElemAST noeud = e();
            terminal(Terminal.Type.FINPARENTHESE);
            return noeud;
        }else if (terminalCourant.type == Terminal.Type.PLUS || terminalCourant.type == Terminal.Type.FOIS || terminalCourant.type == Terminal.Type.DIVISE || terminalCourant.type == Terminal.Type.MOINS){
            ErreurSynt("Une opération est à un endroit invalide : " + lexical.getLastChars() );
        } else {
            ErreurSynt("Opérande manquante dans la parenthèse : " + lexical.getLastChars() );
        }

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
      toWriteLect += "Lecture de l'AST trouve : \n " + RacineAST.LectAST() + "\n";
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

