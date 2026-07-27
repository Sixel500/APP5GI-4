package app6;

/** @author Ahmed Khoumsi */

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {

  
  /** Evaluation d'AST
   */
    public abstract int EvalAST();

    public abstract Terminal.Type getType();

    /** Lecture d'AST
   */
    public abstract String LectAST();

    public abstract String LectInfix();
    public abstract String LectPrefix();
    public abstract String LectPostfix();
/** ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
 */  
    public void ErreurEvalAST(String s) {
    System.err.println("Erreur : " + s);
    System.exit(1);
    }

}
