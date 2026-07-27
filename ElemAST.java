package app6;

/** @author Ahmed Khoumsi */

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {

  
  /** Evaluation d'AST
   */
  public abstract String EvalAST();


    /** Evaluation d'AST
     */
    public abstract int IntEvalAST();

    public abstract Terminal.Type getType();

    /** Evaluation d'AST
     */
    public abstract String StringEvalAST();
  /** Lecture d'AST
   */
  public abstract String LectAST();


/** ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
 */  
  public void ErreurEvalAST(String s) {	
    System.err.println("Erreur : " + s);
    System.exit(1);
  }

}
